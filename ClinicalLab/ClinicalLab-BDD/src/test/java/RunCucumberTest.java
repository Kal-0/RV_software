import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME; // Para definir onde estão os step definitions
import static io.cucumber.core.options.Constants.FEATURES_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("resources")  // Seleciona o diretório dos arquivos .feature 
//@SelectPackages("")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty") // Define o formato de saída como "pretty" (para saída mais legível)
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features") // Define o caminho para os arquivos de feature. 

public class RunCucumberTest {
}