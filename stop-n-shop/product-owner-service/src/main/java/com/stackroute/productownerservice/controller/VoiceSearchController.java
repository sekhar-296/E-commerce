//package com.stackroute.productownerservice.controller;
//
//import com.stackroute.productownerservice.model.Type;
//import edu.stanford.nlp.ling.CoreAnnotations;
//import edu.stanford.nlp.ling.CoreLabel;
//import edu.stanford.nlp.pipeline.CoreDocument;
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Properties;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:4200")
//public class VoiceSearchController {
//    private static StanfordCoreNLP stanfordCoreNLP;
////    private Pipeline pipeline;
//
////    @Autowired
////    public VoiceSearchController(Pipeline pipeline) {
//////        this.stanfordCoreNLP = stanfordCoreNLP;
////        this.pipeline=pipeline;
////    }
//
//    @PostMapping("/voicesearch")
//    public Set<String> ner(@RequestBody String text, @RequestParam Type type){
//        CoreDocument coreDocument=new CoreDocument(text);
//        stanfordCoreNLP.annotate(coreDocument);
//        List<CoreLabel> coreLabels=coreDocument.tokens();
//        return new HashSet<>(collectList(coreLabels,type));
//    }
//
//    public List<String> collectList(List<CoreLabel> coreLabels, Type type){
//        return  coreLabels
//                .stream()
//                .filter(coreLabel -> type.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
//                .map(CoreLabel::originalText)
//                .collect(Collectors.toList());
//    }
//
//    private static Properties properties;
//    private static String propertiesName="tokenize, ssplit, pos , lemma, ner";
////    private static StanfordCoreNLP stanfordCoreNLP;
//
////    private Pipeline(){
////
////    }
//    static{
//        properties=new Properties();
//        properties.setProperty("annotators",propertiesName);
//    }
//
//    //    @Bean(name = "stanfordCoreNLP")
//    public static StanfordCoreNLP getInstance(){
//        if(stanfordCoreNLP==null){
//            stanfordCoreNLP=new StanfordCoreNLP(properties);
//        }
//        return stanfordCoreNLP;
//    }
//}
