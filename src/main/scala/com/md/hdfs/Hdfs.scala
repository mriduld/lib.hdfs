package com.md.hdfs

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileStatus, FileSystem, Path}
import zio.RIO

object Hdfs {
	def fileSystem(path: Path): Hdfs[FileSystem] = 
		for {
			conf <- RIO.environment[Configuration]
			fs   <- RIO(FileSystem.get(path.toUri, conf))
		} yield fs

	def withFileSystem[A](path: Path)(f: FileSystem => A): Hdfs[A] =
		for {
	      fs <- fileSystem(path)
	      a  <- RIO(f(fs))
	    } yield a

	def exists(path: Path): Hdfs[Boolean] =
    	withFileSystem(path)(_.exists(path))    
}