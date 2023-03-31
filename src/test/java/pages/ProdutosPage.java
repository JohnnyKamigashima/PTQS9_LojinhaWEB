package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProdutosPage {
    private WebDriver navegador;
    public ProdutosPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public CadastroPage clicarLinkAdicionarProduto() {
        navegador.findElement(By.xpath("//a[@class='waves-effect waves-light btn right']")).click();
        return new CadastroPage(navegador);
    }

    public String extrairMensagemDeErro() {
       return new String(navegador.findElement(By.xpath("//div[@class='toast rounded']")).getText());
    }
}
