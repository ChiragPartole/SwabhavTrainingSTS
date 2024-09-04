package com.aurionpro.batchprocessing.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.aurionpro.batchprocessing.entity.Employee;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class BatchConfig {

	@Bean
    public JdbcCursorItemReader<Employee> databaseReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Employee>()
            .dataSource(dataSource)
            .name("employeeJdbcCursorItemReader")
            .sql("SELECT employee_id, name, salary FROM employees")
            .beanRowMapper(Employee.class)
            .build();
    }

    @Bean
    public FlatFileItemWriter<Employee> csvWriter() {
        return new FlatFileItemWriterBuilder<Employee>()
            .name("employeeCsvWriter")
            .resource(new FileSystemResource("src/main/resources/employees.csv"))
            .delimited()
            .delimiter(",")
            .names("employeeId", "name", "salary")
            .build();
    }
    @Bean
	public DataSourceTransactionManager transactionManager(DataSource data) {
		return new DataSourceTransactionManager(data);
	} 

    @Bean
    public Step exportStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager, 
            JdbcCursorItemReader<Employee> reader, FlatFileItemWriter<Employee> writer) {
        return new StepBuilder("exportCsvStep", jobRepository)
            .<Employee, Employee>chunk(10, transactionManager)
            .reader(reader)
            .writer(writer)
            .build();
    }
    

    @Bean
    public Job exportUserJob(JobRepository jobRepository, Step exportStep, JobCompletionNotificationListener completionNotificationListener) {
        return new JobBuilder("exportUserJob", jobRepository)
            .listener(completionNotificationListener)
            .start(exportStep)
            .build();
    }
}