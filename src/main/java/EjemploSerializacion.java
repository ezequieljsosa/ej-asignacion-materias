import ar.edu.utn.materias.Alumno;
import ar.edu.utn.materias.Curso;
import ar.edu.utn.materias.Materia;
import ar.edu.utn.materias.TipoMateria;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class EjemploSerializacion {

    final static Logger log = LoggerFactory.getLogger(EjemploSerializacion.class);

    public static void main(String[] args) throws Exception {


        ArgumentParser parser = ArgumentParsers.newFor("csvParser").build()
                .description("selecciona de un csv");
        parser.addArgument("--format")
                .type(String.class).choices("json", "xml").setDefault("json")
                .help("formato");

        Namespace res = null;
        try {
            res = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.printHelp();
            log.error("error ejecutando el comando",e);
            System.exit(1);
        }

        log.debug("creando Alumno...");
        Alumno alumno = new Alumno("Pepe","Lopez", LocalDate.now());
        Materia materia = new Materia("Desarrollo de programas");
        materia.setDescripcion("Materia que enseña a programar");
        Curso curso = new Curso(materia,2019, TipoMateria.CUATRIMESTRAL);
        alumno.anotarEn(curso);
        log.debug("Alumno creado...");
        String json = "";
        if(res.getString("format").equals("json")){
            ObjectMapper objectMapper = new ObjectMapper();
            JavaTimeModule javaTimeModule=new JavaTimeModule();
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
            objectMapper.registerModule(javaTimeModule);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            log.debug("tratando de serializa alumno...");
            json = objectMapper.writeValueAsString(alumno);
        }
        if(res.getString("format").equals("xml")){
            XmlMapper objectMapper = new XmlMapper();
            JavaTimeModule javaTimeModule=new JavaTimeModule();
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
            objectMapper.registerModule(javaTimeModule);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            json = objectMapper.writeValueAsString(alumno);
        }



        System.out.println(json);

        log.info("Alumno serializado OK");
    }
}
