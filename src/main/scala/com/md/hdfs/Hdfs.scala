package com.md.hdfs

import java.io.IOException

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import zio.ZIO

object Hdfs {
  def safeIO[A](a: => A): Hdfs[A] =
    ZIO(a).catchAll {
      case e: IOException => ZIO.fail(HdfsIOError(e))
      case e: Exception   => ZIO.fail(MessageHdfsError(e.getMessage))
    }

  def fileSystem(path: Path): Hdfs[FileSystem] =
    ZIO.accessM[Configuration]{ conf =>
      safeIO(FileSystem.get(path.toUri, conf))
    }

  def withFileSystem[A](path: Path)(f: FileSystem => A): Hdfs[A] =
    for {
      fs <- fileSystem(path)
      a  <- safeIO(f(fs))
    } yield a

  def exists(path: Path): Hdfs[Boolean] =
    withFileSystem(path)(_.exists(path))
}

sealed trait HdfsError
case class HdfsIOError(error: IOException) extends HdfsError
case class MessageHdfsError(message: String) extends HdfsError