/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;

import ApiHackerEarth.com.hackerearth.heapi.sdk.options.RunOptions;
import ApiHackerEarth.com.hackerearth.heapi.sdk.options.SupportedLanguages;
import ApiHackerEarth.com.hackerearth.heapi.sdk.requests.RunRequest;
import ApiHackerEarth.com.hackerearth.heapi.sdk.responses.RunResponse;
import DaoObj.daoProbleme;
import DaoObj.daoSolution;
import Enumeration.Level;
import Obj.Probleme;
import Obj.Solution;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JFileChooser;

/**
 *
 * @author Amal
 */
public class AjoutProblm {
    
    @FXML
    private Button Sol;
    
    @FXML
    private TextField Titre;
    
    @FXML
    private Button Test;
    
    @FXML
    private TextField Niveau;
    
    @FXML
    private Button Annuler;
    
    @FXML
    private Button Enregistrer;
    
    @FXML
    private Button Prblm;
    
    File ProblmFile;
    File SolFile;
    File TestFile;
    daoProbleme daoP = new daoProbleme(Layout_adminController.db);
    daoSolution daoS = new daoSolution(Layout_adminController.db);
    Alert Al = new Alert();
    static String id;
    
    @FXML
    private File OpenChooser() {
        JFileChooser fs = new JFileChooser(new File("c:\\"));
        fs.setDialogTitle("Save a File");
        int result = fs.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File source = fs.getSelectedFile();
            return source;
//            File dest = new File("submissions/Readit.jpg");
//            try {
//                Files.copy(source.toPath(), dest.toPath(),  REPLACE_EXISTING);
//            } catch (IOException ex) {
//                System.out.println("eroooooror");
//            }
        }
        return null;
    }
    
    @FXML
    void AjoutPrblm(ActionEvent event) {
//        Probleme P = new Probleme(null, null, null, Level.EASY, null)
//        File dest = new File(null);
        ProblmFile = OpenChooser();
        
    }
    
    @FXML
    void AjoutSol(ActionEvent event) {
        SolFile = OpenChooser();
//        //FilenameUtils.getExtension(SolFile.getName());

    }
    
    private static String getFileExtension(File file) {
        String extension = "";
        
        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }
        
        return extension;
        
    }
    
    @FXML
    void AjoutTest(ActionEvent event) {
        TestFile = OpenChooser();
    }
    
    @FXML
    void EnregistrerDef(ActionEvent event) {
        if ((TestFile != null) && (SolFile != null) && (TestFile != null)) {
            Probleme P = new Probleme(Titre.getText(), Level.valueOf(Niveau.getText()), getFileExtension(ProblmFile), getFileExtension(TestFile));
            daoP.create(P);
            //System.out.println("zed zed");
            Solution S = new Solution(P.getId_Probleme(), null, getFileExtension(SolFile));
            if (daoS.create(S)) {
                try {
                    File T = new File(P.getPathTest());
                    File Pr = new File(P.getPathPb());
                    File Solo = new File(S.getPath());
                    Files.copy(TestFile.toPath(), T.toPath(), REPLACE_EXISTING);
                    Files.copy(ProblmFile.toPath(), Pr.toPath(), REPLACE_EXISTING);
                    Files.copy(SolFile.toPath(), Solo.toPath(), REPLACE_EXISTING);
                    
                    String Soli = getInsideFile(SolFile);
                    // System.out.println("The sol is " + Soli);
                    String Testi = getInsideFile(TestFile);
                //getInsideFile(TestFile);
                    //   System.out.println("The test is "+Testi);
                    getTheRunSolution(Soli, Testi, S);
                    Al.AllGood("Message ajoute avec succes", event);
                    
                } catch (IOException ex) {
                    Al.AlerErreur("erreur", event);
                }
            }
            
        } else {
            //System.out.println("Not that okey");
            Al.AlerErreur("erreur", event);
        }
    }
    
    private void getTheRunSolution(String Soli, String Testi, Solution S) {
        RunOptions RO = new RunOptions(Soli, Testi, SupportedLanguages.CPP);
        RunRequest RR = new RunRequest("efebd479faa2a20e2003269dea2a644e515190c8", RO);
        RunResponse RResp = RR.Execute();
        File file = new File("SolutionsRun/" + S.getId_Probleme());
        //System.out.println("SolutionsRun/"+S.getId_Probleme()+".cpp");
        System.out.println(RResp.getRunStatus().getOutput());
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(RResp.getRunStatus().getOutput());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //return file;
        //System.out.println("status runtime " + RResp.getRunStatus().getOutput());
    }
    
    private String getInsideFile(File file) throws IOException {
        String Res = new String();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String st;
            
            while ((st = br.readLine()) != null) {
                
                Res += st + "\n";
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DetailProController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailProController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DetailProController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return Res;
    }
    
    @FXML
    void Annuler(ActionEvent event) {
        //are you sure o dekchi 
        Al.Annuler("est ce que vous etes sur de vouloir annuler?", event);
    }
}
