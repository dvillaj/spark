var vertices = sc.textFile("metadata-raw.txt").
 flatMap { line => line.split("\\s+") }.distinct()
vertices.map { vertex => vertex.replace("-", "") + "\t" + vertex }.
 saveAsTextFile("metadata-lookup")
sc.textFile("metadata-raw.txt").map { line =>
 var fields = line.split("\\s+")
 if (fields.length == 2) {
   fields(0).replace("-", "") + "\t" + fields(1).replace("-", "")
 }
}.saveAsTextFile("metadata-processed")
