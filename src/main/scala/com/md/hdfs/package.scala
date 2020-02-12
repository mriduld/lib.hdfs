package com.md

import zio.RIO
import java.io.IOException
import org.apache.hadoop.conf.Configuration

package object hdfs {
	type Hdfs[A] = RIO[Configuration, A]
}