package testeInterface;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testes {
	private static WebDriver driver;
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}
	@Test
	public void passSenhaTest() throws Exception{
		
		driver.get("localhost:8000");
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement novaSerie = driver.findElement(By.linkText("Criar Novo"));
		novaSerie.click();
		WebElement loginSerie = driver.findElement(By.name("email"));
		loginSerie.clear();
		loginSerie.click();
		wait.until(ExpectedConditions.elementToBeClickable(loginSerie));
		loginSerie.sendKeys("teste@teste");
		loginSerie = driver.findElement(By.id("password"));
		loginSerie.clear();
		loginSerie.click();
		wait.until(ExpectedConditions.elementToBeClickable(loginSerie));
		loginSerie.sendKeys("teste");
		
		novaSerie = driver.findElement(By.className("btn"));
		novaSerie.submit();
		
		boolean foundWords = driver.getPageSource().contains("Deslogar");
		assertTrue(foundWords);
	}
	
	@Test
	public void editarSerieTest() throws Exception{
		
		driver.get("localhost:8000");
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		WebElement novaSerie = driver.findElement(By.linkText("Criar Novo"));
		novaSerie.click();
		
		WebElement loginSerie = driver.findElement(By.name("email"));
		wait.until(ExpectedConditions.elementToBeClickable(loginSerie));
		loginSerie.clear();
		loginSerie.click();
		loginSerie.sendKeys("teste@teste");
		loginSerie = driver.findElement(By.id("password"));
		wait.until(ExpectedConditions.elementToBeClickable(loginSerie));
		loginSerie.clear();
		loginSerie.click();
		loginSerie.sendKeys("teste");
		loginSerie= driver.findElement(By.className("btn"));
		wait.until(ExpectedConditions.elementToBeClickable(loginSerie));
		loginSerie.submit();
		
		WebElement element = driver.findElement(By.linkText("Editar"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		
		WebElement selected = driver.findElement(By.id("genero"));
		wait.until(ExpectedConditions.elementToBeClickable(selected));
		Select selectByValue = new Select(selected);
		selectByValue.selectByVisibleText("Aventura");
		
		WebElement elementCad = driver.findElement(By.name("assistido"));
		wait.until(ExpectedConditions.elementToBeClickable(elementCad));
		elementCad.clear();
		elementCad.click();
		elementCad.sendKeys("sim");
		elementCad = driver.findElement(By.id("avaliacao"));
		elementCad.clear();
		elementCad.click();
		elementCad.sendKeys("7");
		elementCad=driver.findElement(By.className("btn"));
		elementCad.submit();
		
		boolean foundWords = driver.getPageSource().contains("Aventura");
		assertTrue(foundWords);
	}
	
	@Test
	public void imagemSerieTest() throws Exception{
		driver.get("localhost:8000");
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		WebElement novaSerie = driver.findElement(By.linkText("Criar Novo"));
		novaSerie.click();
		
		WebElement loginSerie = driver.findElement(By.name("email"));
		wait.until(ExpectedConditions.elementToBeClickable(loginSerie));
		loginSerie.clear();
		loginSerie.click();
		loginSerie.sendKeys("teste@teste");
		loginSerie = driver.findElement(By.id("password"));
		wait.until(ExpectedConditions.elementToBeClickable(loginSerie));
		loginSerie.clear();
		loginSerie.click();
		loginSerie.sendKeys("teste");
		loginSerie= driver.findElement(By.className("btn"));
		wait.until(ExpectedConditions.elementToBeClickable(loginSerie));
		loginSerie.submit();
		
		WebElement element = driver.findElement(By.linkText("Detalhes"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		
		element = driver.findElement(By.linkText("Editar"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		
		loginSerie = driver.findElement(By.id("imagem"));
		wait.until(ExpectedConditions.elementToBeClickable(loginSerie));
		loginSerie.sendKeys("C:\\teste.png");
		
		element = driver.findElement(By.id("numero"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.clear();
		element.click();
		element.sendKeys("5");
		loginSerie = driver.findElement(By.className("btn"));
		loginSerie.submit();
		
		boolean foundWords = driver.getPageSource().contains("5ª temporada");
		assertTrue(foundWords);
	}
	@After
	public void tearDown() throws Exception{
		driver.close();
		driver.quit();
	}
}
