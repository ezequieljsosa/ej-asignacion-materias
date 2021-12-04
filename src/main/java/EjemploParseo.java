import ar.edu.utn.materias.Alumno;
import ar.edu.utn.materias.Curso;
import ar.edu.utn.materias.Materia;
import ar.edu.utn.materias.TipoMateria;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class EjemploParseo {

    final static Logger log = LoggerFactory.getLogger(EjemploParseo.class);

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



        Alumno alumno1 = null;
        if(res.getString("format").equals("json")){
            ObjectMapper objectMapper = new ObjectMapper();

            JavaTimeModule javaTimeModule=new JavaTimeModule();
            // Hack time module to allow 'Z' at the end of string (i.e. javascript json's)
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
            objectMapper.registerModule(javaTimeModule);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            File file = new File("src/main/resources/alumno.json");
             alumno1 = objectMapper.readValue(file,Alumno.class);
        }
        if(res.getString("format").equals("xml")){
            XmlMapper objectMapper = new XmlMapper();
            JavaTimeModule javaTimeModule=new JavaTimeModule();
            // Hack time module to allow 'Z' at the end of string (i.e. javascript json's)
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
            objectMapper.registerModule(javaTimeModule);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            File file = new File("src/main/resources/alumno.xml");
             alumno1 = objectMapper.readValue(file,Alumno.class);
        }

        System.out.println(alumno1);



        log.info("Alumno parseado OK");
    }
}
