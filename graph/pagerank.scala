import org.apache.spark.graphx._
// Load the edges as a graph
val graph = GraphLoader.edgeListFile(sc, "metadata-processed")
// Run PageRank
val ranks = graph.pageRank(0.0001).vertices
// join the ids with the phone numbers
val entities = sc.textFile("metadata-lookup").map { line =>
 val fields = line.split("\\s+")
 (fields(0).toLong, fields(1))
}
val ranksByVertex = entities.join(ranks).map {
 case (id, (vertex, rank)) => (rank, vertex)
}
// print out the top 5 entities
println(ranksByVertex.sortByKey(false).take(5).mkString("\n"))
