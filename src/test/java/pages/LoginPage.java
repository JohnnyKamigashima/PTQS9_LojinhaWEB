package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver navegador;
    public LoginPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public LoginPage preencheUsuario(String usuario) {
        navegador.findElement(By.xpath("//label[@for='usuario']")).click();
        navegador.findElement(By.xpath("//input[@id='usuario']"))
                .sendKeys(usuario);
        return this;
    }

    public LoginPage preencheSenha(String senha) {
        navegador.findElement(By.xpath("//label[@for='senha']")).click();
        navegador.findElement(By.xpath("//input[@id='senha']")).sendKeys(senha);
        return this;
    }

    public ProdutosPage clicaBotaoEntrar() {
        navegador.findElement(By.xpath("//button[@name='action']")).click();
        return new ProdutosPage(navegador);
    }
}
