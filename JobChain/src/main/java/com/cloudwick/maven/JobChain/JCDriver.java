package com.cloudwick.maven.JobChain;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class JCDriver extends Configured implements Tool {

	public int run(String[] args) throws Exception {

        if (args.length != 5) {
            System.out.printf(
                    "Usage: %s [generic options] <input dir> <output dir>\n", getClass()
                    .getSimpleName());
            ToolRunner.printGenericCommandUsage(System.out);
            return -1;
        }

        Configuration conf = new Configuration();
        conf.set("location", args[2]);
        conf.set("minSalary", args[3]);
        conf.set("maxSalary", args[4]);
        Job job = new Job(conf);
        
        Configuration mapAConf = new Configuration(false);
        ChainMapper.addMapper(job, JCLocationFilterMapper.class, LongWritable.class, Text.class,Text.class, Text.class, mapAConf);
        
        Configuration mapBConf = new Configuration(false);
        ChainMapper.addMapper(job, JCSalaryFilterMapper.class, Text.class, Text.class, Text.class, Text.class, mapBConf);
        
        job.setJarByClass(JCDriver.class);
        job.setJobName(this.getClass().getName());

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        job.setNumReduceTasks(0);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);


        if (job.waitForCompletion(true)) {
            return 0;
        }
        return 1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new JCDriver(), args);
        System.exit(exitCode);
    }

}
