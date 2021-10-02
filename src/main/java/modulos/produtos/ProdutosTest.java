package modulos.produtos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName ("Testes WEB do módulo de produtos")
public class ProdutosTest {
    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a ZERO")
    public void testNaoEpermitidoRegistrarProdutoComValorIgualAzero(){
        // Abrir o Navegador
        System.setProperty("webdriver.chrome.driver","C:\\driverChrome\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        
        // Maximizar a tela
        navegador.manage().window().maximize();

        // Vou definir um tempo de espera padrão
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navegar para a página da Lojinha WEB
        navegador.get("http://165.227.93.41/lojinha-web/v2/");

        // Fazer Login
        navegador.findElement(By.cssSelector("label[for='usuario']")).click();
        navegador.findElement(By.id("usuario")).sendKeys("admin");

        navegador.findElement(By.cssSelector("label[for='senha']")).click();
        navegador.findElement(By.id("senha")).sendKeys("admin");
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        // Vou para a tela de Registro do Produto
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        // Vou preencher dados do produto e o valor será igual a ZERO
        navegador.findElement(By.id("produtonome")).sendKeys("Macbook Pro");
        navegador.findElement(By.name("produtovalor")).sendKeys("000");
        navegador.findElement(By.id("produtocores")).sendKeys("rosa, verde");

        // Vou submeter o formulário
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        // Vou validar que a mensagem de erro foi apresentada DIFICIL!!!
        // <div class="toast rounded" style="top: 0px; transition: transform 0.2s ease 0s, opacity 0.2s ease 0s;">O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00</div>
        String msgToast = navegador.findElement(By.cssSelector(".toast.rounded")).getText();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00",msgToast);

        // Vou fechar o navegador
        navegador.quit();
    }

}
