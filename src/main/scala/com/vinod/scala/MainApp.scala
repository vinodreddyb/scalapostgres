package com.vinod.scala




import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Success, Failure}
import slick.jdbc.PostgresProfile.api._
object MainApp extends App {

  val dbConfig = Database.forConfig("database");
  val accountsDAO = new AccountsDAO(dbConfig)

  try {
   // dbConfig.run(accountsDAO.schema.createIfNotExists)


    val account = accountsDAO.create(Account(name = "Reddy"))
    account onComplete {
      case Success(account) => println(s"Inserted account with ID  ${account.id} ::: Name ${account.name}")
      case Failure(t) => println("An error has occurred insert: " + t.getMessage)
    }
    /*val account: Future[Account] =create(Account(1,"Vinod"))

    account onComplete {
      case Success(account) => println(s"Inserted account with ID  ${account.id} ::: Name ${account.name}")
      case Failure(t) => println("An error has occurred insert: " + t.getMessage)
    }*/

    accountsDAO.findAll() onComplete {
      case Success(account) => account.foreach(f => println(f.id, f.name))
      case Failure(t) => println("An error has occurred: " + t.getMessage)
    }

  } catch  {
    case e: Exception => println("exception caught: " + e);
  } finally dbConfig.close()


 /* findAll() onComplete {
    case Success(account) => account.foreach(f => println(f.id, f.name))
    case Failure(t) => println("An error has occurred: " + t.getMessage)
  }*/



}
