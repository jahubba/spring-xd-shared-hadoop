package jahubba;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.hadoop.hive.HiveClientFactory;

@Configuration
public class BdsCoreConfiguration implements EnvironmentAware {

	@Autowired
	private Environment env;
	
	
	@Bean(name = "sparkConf")
	public SparkConf yarnSparkConf() {
		return new SparkConf().setMaster(env.getProperty("spark.master"))
				.setAppName("SpringSpark")
				.set("spark.executor.extraClassPath", "/opt/cloudera/parcels/CDH/lib/hive/lib/*")
				.set("spark.driver.allowMultipleContexts", "true");
	}
	
	@Bean(name = "sparkContext")
	public SparkContext yarnSparkContext() {
		return new SparkContext(yarnSparkConf());
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}

	@Bean(name = "hiveClientFactory")
	public HiveClientFactory hiveFactoryNull() {
		return null;
	}

}
