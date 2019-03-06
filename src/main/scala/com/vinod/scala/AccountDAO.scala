package com.vinod.scala

import slick.jdbc.{JdbcProfile, PostgresProfile}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


  case class Account(id: Long =0L , name: String)

  class Accounts(tag: Tag) extends Table[Account](tag, "accounts") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    override def * = (id, name).mapTo[Account]
  }

  class AccountsDAO(db: Database) extends TableQuery(new Accounts(_)) {



    def findById(id: Long): Future[Option[Account]] = {
      db.run(this.filter(_.id === id).result).map(_.headOption)
    }

    def create(account:Account) : Future[Account] = {
      db.run(this returning this.map(_.id) into ((acc, id) => acc.copy(id = id)) += account)
    }

    def deleteById(id:Long): Future[Int] = {
      db.run(this.filter(_.id === id).delete)
    }

    def findAll() : Future[Seq[Account]] = {
      db.run(this.result)
    }


  }






