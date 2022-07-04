package br.com.carlosnoronha.tasks.funtional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TasksTest {

    public WebDriver acessarAplicacao(){
        WebDriver driver = new ChromeDriver ();
        driver.navigate ().to("http://localhost:8001/tasks/");
        driver.manage ().timeouts ().implicitlyWait (Duration.ofMillis (1000));
        return driver;
    }


    @Test
    public void deveSalvarTarefaComSucesso() throws InterruptedException {
       WebDriver driver = acessarAplicacao ();
      try {

          //clicar em add Todo
          driver.findElement (By.id ("addTodo")).click ();
          //escrever descrição
          driver.findElement (By.id ("task")).sendKeys ("Salvar Tarefa Com Sucesso");
          //escrever a data
          driver.findElement (By.id ("dueDate")).sendKeys ("07/01/2089");
          //clicar em salvar
          driver.findElement (By.id ("saveButton")).click ();
          Thread.sleep (1000);
          //validar mensagem de sucesso
          Assert.assertEquals ( "Success!"  ,driver.findElement (By.id ("message")).getText ());
      }finally {

          driver.quit ();
      }
    }
    @Test
    public void naoDeveSalvarTarefaSemDescricao() throws InterruptedException {
       WebDriver driver = acessarAplicacao ();
        //clicar em add Todo
        driver.findElement (By.id ("addTodo")).click ();
      //escrever a data
        driver.findElement (By.id ("dueDate")).sendKeys ("07/01/2089");
        //clicar em salvar
        driver.findElement (By.id ("saveButton")).click ();
        Thread.sleep (1000);
        //validar mensagem de sucesso
        Assert.assertEquals ( "Fill the task description"  ,driver.findElement (By.id ("message")).getText ());
        driver.quit ();
    }
    @Test
    public void naoDeveSalvarTarefaSemData() throws InterruptedException {
        WebDriver driver = acessarAplicacao ();
        //clicar em add Todo
        driver.findElement (By.id ("addTodo")).click ();
        //escrever a data
        driver.findElement (By.id ("task")).sendKeys ("curso CD/CI");

        //clicar em salvar
        driver.findElement (By.id ("saveButton")).click ();
        Thread.sleep (1000);
        //validar mensagem de sucesso
        Assert.assertEquals ( "Fill the due date"  ,driver.findElement (By.id ("message")).getText ());
        driver.quit ();
    }
    @Test
    public void deveSalvarTarefaComDataPassada() throws InterruptedException {
        WebDriver driver = acessarAplicacao ();
        //clicar em add Todo
        driver.findElement (By.id ("addTodo")).click ();
        //escrever descrição
        driver.findElement (By.id ("task")).sendKeys ("Tarefa Com Data Passada");
        //escrever a data
        driver.findElement (By.id ("dueDate")).sendKeys ("07/01/2019");
        //clicar em salvar
        driver.findElement (By.id ("saveButton")).click ();
        Thread.sleep (1000);
        //validar mensagem de sucesso
        Assert.assertEquals ( "Due date must not be in past"  ,driver.findElement (By.id ("message")).getText ());
        driver.quit ();
    }
}
