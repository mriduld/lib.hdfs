package com.md

import org.apache.hadoop.conf.Configuration
import zio.ZIO

package object hdfs {
	//type Hdfs[A] = RIO[Configuration, A]
	type Hdfs[A] = ZIO[Configuration, HdfsError, A]
}