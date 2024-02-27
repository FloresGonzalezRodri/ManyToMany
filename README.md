<h1 align="left">Prueba de Asociaciones ManyToMany con JUnit</h1>

###

<p align="left">Proyecto que testea asociaciones entre entidades muchos a muchos, con y sin entidad intermedia, en una base de datos embebida</p>

###

<h2 align="left">Tecnologías empleadas</h2>

###

<div align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="40" alt="java logo"  />
  <img width="12" />
  <img src="https://skillicons.dev/icons?i=hibernate" height="40" alt="hibernate logo"  />
  <img width="12" />
  <img src="https://cdn.simpleicons.org/spring/6DB33F" height="40" alt="spring logo"  />
</div>

###

<h2 align="left">Creación de proyecto</h2>

###

<p align="left">1. Crear proyecto spring version 3.2.2 con los starters:<br>a. spring-boot-starter-jpa<br>b. spring-boot-starter-test<br>c. lombok<br>Agregar las dependencias:<br>d. h2<br>plugins:<br>a. spring-boot-maven-plugin</p>

###

<h2 align="left">Test Junit escritos</h2>

###

<p align="left">1️⃣ Persistencia en cascada: desde Alumno a Curso<br>2️⃣ Eliminación en cascada desde Alumno a Curso<br>3️⃣ Operación merge() sobre un Alumno, después de cerrar el contexto de persitencia<br>4️⃣ Operación merge() sobre un Alumno, después de aplicarle detach()<br>5️⃣ Operación refresh() sobre un Alumno<br>6️⃣  Prueba la peristencia en cascada desde Curso a Alumno<br>7️⃣ Persistencia en cascada desde CategoriaItem hacia Categoria y Item</p>

###

<h3 align="left">Notas</h3>

###

<p align="left">⚠️ La clase embebida debe ser estatica, para que hibernate pueda crear instancias de esa clase independientemente de la clase propietaria. <br><br>⚠️No olvidar mantener coherencias de relaciones bidireccionales<br>en constructores y al agregar relaciones<br><br>⚠️ Ver comentarios de codigo, para consultar otra alternativa para mapear la PRIMARY KEY de la entidad intermedia</p>

###
