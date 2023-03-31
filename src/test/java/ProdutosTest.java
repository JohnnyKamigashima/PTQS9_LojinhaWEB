import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import pojo.Configura;
import pojo.Produto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@DisplayName("Testes Web do Módulo de Produtos")
class ProdutosTest {
    ObjectMapper mapper = new ObjectMapper();
    ProdutosTest() throws IOException {
    }

    public String leArquivoJson(String nomeArquivo) throws IOException {
        return new String(Files.readAllBytes(Paths.get(nomeArquivo)));
    }

    String configJson = leArquivoJson("src/test/java/resources/json/config.json");
    String configUsuarioJson = leArquivoJson("src/test/java/resources/json/usuario.json");
    String configProdutoJson = leArquivoJson("src/test/java/resources/json/produto1.json");
    Configura config = mapper.readValue(configJson, Configura.class);
    Produto produto = mapper.readValue(configProdutoJson, Produto.class);

    WebDriver navegador;
    @BeforeEach
    void setUp() {
    //Abrir o navegador
        System.setProperty("webdriver.chrome.driver", config.getChromeDriverPath());

    //Nova implementação do chromedriver 111.0.55+ exige a adição de uma opção para permitir o acesso remoto
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

    //Instanciar o navegador
        this.navegador = new ChromeDriver(options);

    // Maximize a janela do navegador
        navegador.manage().window().maximize();

    //Navegar para a página da Lojinha Web
        navegador.get(config.getBaseUrl());
    }

    @AfterEach
    void tearDown() {
    //Fechar o navegador
        navegador.quit();
    }
    @DisplayName("Não é permitido registrar um produto com valor ")
    @ParameterizedTest(name = "Teste {index} - {displayName} R$ {0}")
    @CsvFileSource(resources = "/csv/massaProdutoValores.csv", numLinesToSkip = 1, delimiter = ';')
    void testeDeProdutoComValor(String valor, String resultadoEsperado) {

    // Fazer Login
        String resultadoObtido = new LoginPage(navegador)
            .preencheUsuario("admin")
            .preencheSenha("admin")
            .clicaBotaoEntrar()
            .clicarLinkAdicionarProduto()
            .preencheNomeDoProduto(produto.getNome())
            .preencheValorDoProduto(valor)
            .preencheCoreDoProduto(produto.getCor())
            .clicaBotaoSalvar()
            .extrairMensagemDeErro();

        //Validar que a mensagem foi apresentada
        assert resultadoObtido.equals(resultadoEsperado);
    }
}
