package User.Controller;

import Admin.Controller.DetailProController;
import DaoObj.DaoCompetition;
import DaoObj.daoProbleme;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.web.HTMLEditor;
import com.google.gson.Gson;
import ApiHackerEarth.com.hackerearth.heapi.sdk.client.HackerEarthAPI;
import ApiHackerEarth.com.hackerearth.heapi.sdk.options.CompileOptions;
import ApiHackerEarth.com.hackerearth.heapi.sdk.options.RunOptions;
import ApiHackerEarth.com.hackerearth.heapi.sdk.options.SupportedLanguages;
import ApiHackerEarth.com.hackerearth.heapi.sdk.requests.CompileRequest;
import ApiHackerEarth.com.hackerearth.heapi.sdk.requests.RunRequest;
import ApiHackerEarth.com.hackerearth.heapi.sdk.responses.CompileResponse;
import ApiHackerEarth.com.hackerearth.heapi.sdk.responses.RunResponse;
import DaoObj.daoParticipe;
import DaoObj.daoSubmit;
import DaoObj.daoSubmit_Comp;
import Database.Database;
import Obj.Submit;
import Obj.Submit_In_Comp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author khalf
 */
public class submitController implements Initializable {

    @FXML
    private MenuItem JAVA = new MenuItem("Java");
    @FXML
    private MenuItem C = new MenuItem("C");
    @FXML
    private MenuItem PYTHON = new MenuItem("PYTHON");

    @FXML
    private MenuItem CPLUSCPLUS = new MenuItem("C++");

    @FXML
    private Button btn_submit;
    @FXML
    private Button bnt_annuler;
    @FXML
    private TextArea editor;
    Admin.Controller.Alert Al;
    @FXML
    private MenuButton Language = new MenuButton("Language");
    private String Lang;
    public static String IdPro;
    public static String IdUser;
    daoProbleme daoP;
    DaoCompetition daoC;
    daoSubmit daoS;
    daoSubmit_Comp daoSINC;
    Vector<String> ListeCompetUserParticipe = new Vector<String>(0);
    public static Database db;
    // public static String Id ;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //IdUser = layoutController.Id;
        // System.out.println("----------1--------------"); 
        daoC = new DaoCompetition(db);
        daoP = new daoProbleme(db);
        // System.out.println("----------1--------------"); 
        daoS = new daoSubmit(db);
        daoSINC = new daoSubmit_Comp(db);
//         System.out.println("----------1--------------"); 
//         System.out.println("The idpro "+IdPro);
//         System.out.println("The IdUser "+IdUser);
        Language = new MenuButton();
        Language.getItems().addAll(JAVA, C, PYTHON, CPLUSCPLUS);
    }

    void CompetUserParticipeWithPrblm() {
        //ga3 les competitions li fihum had prblm
        Vector<String> ListeCompet = daoC.existPr(IdPro);
          
        for (int i = 0; i < ListeCompet.size(); i++) {
            //if had user kayparticiper l chi we7da men dek compet 
            //System.out.println(ListeCompet.elementAt(i));
            if (daoParticipe.existUser(IdUser, ListeCompet.elementAt(i))) {
                System.out.println("element est "+ListeCompet.elementAt(i));
                ListeCompetUserParticipe.add(ListeCompet.elementAt(i));
            }
           //
        }
    }

    @FXML
    private void submit(ActionEvent event) {
        //recuperer solution
        CompetUserParticipeWithPrblm();
         System.out.println("The size other is "+ListeCompetUserParticipe.size());
        if (ListeCompetUserParticipe.size() == 0) {
            //if il participe
             System.out.println("//il ne participe pas");
             NormalSubmit();

        } else {
            System.out.println("il participe");
            SubmitInCompetition();
        }
    }

    void NormalSubmit() {
        //System.out.println("-------------1--------------");
        //System.out.println("The IDPRO IS " + IdPro);
        String source = editor.getText();
        String RunSolution = loadTest("SolutionsRun/" + IdPro );
        String Test = loadTest("Tests/" + IdPro + ".txt");
        //System.out.println("-------------2--------------");
        RunOptions RO = new RunOptions(source, Test, SupportedLanguages.CPP);
        RunRequest RR = new RunRequest("efebd479faa2a20e2003269dea2a644e515190c8", RO);
        RunResponse RResp = RR.Execute();
        //System.out.println("meziananananannanananananna");
        System.out.println(RResp.getRunStatus().getOutput());
        String verdict = null;
        // System.out.println("-------------3--------------");
         
        if (RResp.getRunStatus().getOutput().equals(RunSolution)) {
            verdict = "accepted";
            //System.out.println("accepted");
            Al.AllGood(verdict, null);
        } 
        else {
            verdict = "WA";
            //System.out.println("nON ACCEPTER");
            Al.AlerErreur(verdict, null);

        }
        Submit S = new Submit(IdUser, IdPro, new Date(), verdict, Lang, source, "java");
        daoS.create(S);

    }

    String loadTest(String path) {
        File file = new File(path);
        String Res = new String();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String st;

            while ((st = br.readLine()) != null) {
                Res += st + "\n";
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DetailProController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailProController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DetailProController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (Res);
    }

    void SubmitInCompetition() {

        String source = editor.getText();
        //System.out.println("The IDPRO "+IdPro);
        String RunSolution = loadTest("./SolutionsRun/" + IdPro );
        String Test = loadTest("./Tests/" + IdPro + ".txt");
        RunOptions RO = new RunOptions(source, Test, SupportedLanguages.CPP);
        RunRequest RR = new RunRequest("efebd479faa2a20e2003269dea2a644e515190c8", RO);
        RunResponse RResp = RR.Execute();
        String verdict = null;
        if (RResp.getRunStatus().getOutput().equals(RunSolution)) {
            for (int i = 0; i < ListeCompetUserParticipe.size(); i++) {
                daoParticipe.UpdateScore(IdUser, ListeCompetUserParticipe.elementAt(i), 5, layoutController.db);
                System.out.println("the id user in submit compt is " + IdUser);
                Submit_In_Comp S = new Submit_In_Comp(IdUser, ListeCompetUserParticipe.elementAt(i), IdPro, new Date(), verdict, Lang, editor.getText(), Lang);
                daoSINC.create(S);
            }
            verdict = "accepted";
            //Alert qye c'est accepter
            //System.out.println("accepted");
            Al.AllGood(verdict, null);
        } else {
            for (int i = 0; i < ListeCompetUserParticipe.size(); i++) {
                daoParticipe.UpdateScore(IdUser, ListeCompetUserParticipe.elementAt(i), -5, layoutController.db);
                System.out.println("the id user in submit compt is " + IdUser);
                Submit_In_Comp S = new Submit_In_Comp(IdUser, ListeCompetUserParticipe.elementAt(i), IdPro, new Date(), verdict, Lang, editor.getText(), Lang);
                daoSINC.create(S);
            }
            verdict = "Wrong answer";
            //alert que it's WA a
            //System.out.println("nON ACCEPTER");
            Al.AlerErreur(verdict, null);
        }
        //add submition file 
    }

    @FXML
    void JavaChoose(ActionEvent event) {
        Lang = "Java";
        //Language.setT
    }

    @FXML
    void PythonChoose(ActionEvent event) {
        Lang = "PYTHON";
    }

    @FXML
    void CPLUSPLUSChoose(ActionEvent event) {
        Lang = "CPP";
    }

    @FXML
    void CChoose(ActionEvent event) {
        Lang = "C";
    }

    @FXML
    private void Annuler(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Al.Annuler(Lang, event);
    }

}
