package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroPage {
    private WebDriver navegador;
    public CadastroPage(WebDriver navegador) {
        this.navegador = navegador;
    }
    public CadastroPage preencheNomeDoProduto(String nome) {
        navegador.findElement(By.xpath("//input[@id='produtonome']")).sendKeys(nome);
        return this;
    }

    public CadastroPage preencheValorDoProduto(String valor) {
        navegador.findElement(By.xpath("//input[@id='produtovalor']")).sendKeys(valor);
        return this;
    }

    public CadastroPage preencheCoreDoProduto(String cor) {
        navegador.findElement(By.xpath("//input[@id='produtocores']")).sendKeys(cor);
        return this;
    }

    public ProdutosPage clicaBotaoSalvar() {
        navegador.findElement(By.xpath("//button[@name='action']")).click();
        return new ProdutosPage(navegador);
    }
}
