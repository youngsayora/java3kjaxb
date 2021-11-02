package com.example;

import java.util.*;
import java.io.*;
import javax.xml.bind.*;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JAXBException, IOException
    {
        Group group = new Group();
        group.students = GetFile("jaxb\\src\\main\\java\\com\\example\\students.txt");

        StringWriter writer = new StringWriter();
         //создание объекта Marshaller, который выполняет сериализацию
         JAXBContext context = JAXBContext.newInstance(Student.class, Group.class);
         Marshaller marshaller = context.createMarshaller();
         marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
         // самосериализация
         marshaller.marshal(group, writer);

         try(FileWriter fwriter = new FileWriter("students.xml", false))
        {

           // запись всей строки
            //fwriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE xml>");
            
            fwriter.write(writer.toString());
           
             
            fwriter.flush();
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 


        String filetext = readUsingBufferedReader("students.xml");
        StringReader reader = new StringReader(filetext);

        JAXBContext context1 = JAXBContext.newInstance(Student.class, Group.class);
        Unmarshaller unmarshaller = context1.createUnmarshaller();

        Group group1 = (Group) unmarshaller.unmarshal(reader);

        for(int i = 0; i<group1.students.size() ; i++){
           Student temp = group1.students.get(i);
           temp.print();
           
        }





        
    }




    private static String readUsingBufferedReader(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader (fileName));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( line );
            stringBuilder.append( ls );
        }
        
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }




    public static ArrayList<Student> GetFile(String path){
        ArrayList<Student> value = new ArrayList<>();
        File file = new File(path);
        try {

            int count=0;
            Scanner size2scanner = new Scanner(file);
            while(size2scanner.hasNextLine()) {
                size2scanner.nextLine();
                count++;
            }
            size2scanner.close();




            Scanner scanner = new Scanner(file);

            for (int i = 0; i < count; i++) {
                String[] strings = scanner.nextLine().split(" ");
                Student temp = new Student(strings[0],Double.parseDouble(strings[1]),Integer.parseInt(strings[2]));
                value.add(temp);

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }
   
}
