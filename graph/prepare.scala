// Load metadata-raw.txt File
var vertices = sc.textFile("metadata-raw.txt").
     flatMap { line => line.split("\\s+") }.distinct()

// Clean up vertices as Lookup table
var lookup = vertices.map { vertex => vertex.replace("-", "") + "\t" + vertex }

println("Lookup:")
lookup.foreach(println)
lookup.saveAsTextFile("metadata-lookup")
    
// Load metadata-raw.txt File again to process data
var processed = sc.textFile("metadata-raw.txt").map { line =>
 var fields = line.split("\\s+")
 if (fields.length == 2) {
   fields(0).replace("-", "") + "\t" + fields(1).replace("-", "")
 }
}

println("Processed:")
processed.foreach(println)
    
processed.saveAsTextFile("metadata-processed")
