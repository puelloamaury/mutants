# mutants

Para ejecutar este programa, se debe descargar el repo, abrir como un proyecto en eclipse, refrescar las dependencias de maven y luego ejecutar como una aplicación java, ya que está desarrollado con spring boot.

# Evaluar y almacenar DNA
Para consumir los servicios web, se puede utilizar postman para enviar una petición POST a la URL localhost:8080/mutant con el siguiente payload: 
{"dna":["ATGCGA","ACGTGC","TTAYGT","AGAATG","CCCTTA","TCACTG"]}

# Revisar estadísticas
Para revisar las estadísticas, se hace una petición GET a través de la URL localhost:8080/stats.
