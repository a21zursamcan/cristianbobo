package com.example.ekonos;

import com.example.ekonos.logica.carta;
import com.example.ekonos.logica.empresa;
import com.example.ekonos.logica.jugador;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.example.ekonos.logica.taulell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;



public class HelloController {

    public Image imageMemoria;
    public boolean IntrodueixNomUsuari = false;
    public boolean seleccioDeNouPresident = false;
    taulell t = new taulell();
    public String sonidoClick = "src/main/resources/com/example/ekonos/sonidos/mouseClick.wav";
    public String sonidoCarta = "src/main/resources/com/example/ekonos/sonidos/carta.wav";
    public String sonidoRepartir = "src/main/resources/com/example/ekonos/sonidos/repartirCartas.wav";
    public String sonidoCompra = "src/main/resources/com/example/ekonos/sonidos/sonidoCompraAccion.wav";
    public String sonidoVenta = "src/main/resources/com/example/ekonos/sonidos/sonidoVentaAccion.wav";

    //ImageView accions empreses
    public ArrayList<ImageView> accionsAlphaImatges=new ArrayList<>();
    public ArrayList<ImageView> accionsBetaImatges=new ArrayList<>();
    public ArrayList<ImageView> accionsGammaImatges=new ArrayList<>();
    public ArrayList<ImageView> accionsDeltaImatges=new ArrayList<>();
    public ArrayList<ImageView> accionsEpsilonImatges=new ArrayList<>();
    public ArrayList<ImageView> accionsOmegaImatges=new ArrayList<>();


    @FXML
    public Pane alphaFC1,alphaFC2,alphaFC3,alphaFC4,alphaFC5,alphaFC6,alphaFC7,betaFC1,betaFC2,betaFC3,betaFC4,betaFC5,betaFC6,betaFC7,gammaFC1,gammaFC2,gammaFC3,gammaFC4,gammaFC5,gammaFC6,gammaFC7,deltaFC1,deltaFC2,deltaFC3,deltaFC4,deltaFC5,deltaFC6,deltaFC7,epsilonFC1,epsilonFC2,epsilonFC3,epsilonFC4,epsilonFC5,epsilonFC6,epsilonFC7,omegaFC1,omegaFC2,omegaFC3,omegaFC4,omegaFC5,omegaFC6,omegaFC7;

    @FXML
    public ImageView demoButton;

    @FXML
    private TextField nomJugador;

    @FXML
    public Label alphaJugadorAccions, betaJugadorAccions, gammaJugadorAccions, deltaJugadorAccions, epsilonJugadorAccions, ultimaJugadorAccions, totalJugadorAccions;

    @FXML
    private Label monedas;

    @FXML
    private Button compraAlpha, compraBeta, compraGamma, compraDelta, compraEpsilon, compraOmega, venAlpha, venBeta, venGamma, venDelta, venEpsilon, venOmega;

    @FXML
    //Accions empresa
    public ImageView alphaAccio1,alphaAccio2,alphaAccio3,alphaAccio4,alphaAccio5,alphaAccio6,alphaAccio7,alphaAccio8,betaAccio1,betaAccio2,betaAccio3,betaAccio4,betaAccio5,betaAccio6,betaAccio7,betaAccio8,gammaAccio1,gammaAccio2,gammaAccio3,gammaAccio4,gammaAccio5,gammaAccio6,gammaAccio7,gammaAccio8,deltaAccio1,deltaAccio2,deltaAccio3,deltaAccio4,deltaAccio5,deltaAccio6,deltaAccio7,deltaAccio8,epsilonAccio1,epsilonAccio2,epsilonAccio3,epsilonAccio4,epsilonAccio5,epsilonAccio6,epsilonAccio7,epsilonAccio8,omegaAccio1,omegaAccio2,omegaAccio3,omegaAccio4,omegaAccio5,omegaAccio6,omegaAccio7,omegaAccio8;

    //Imatges pe??es registre
    @FXML
    private ImageView pe??a1, pe??a2, pe??a3, pe??a4, pe??a5, pe??a6, next;

    //Imatges presidents
    public ImageView presidentAlpha, presidentBeta, presidentDelta, presidentEpsilon, presidentGamma, presidentOmega;

    @FXML
    public ImageView casella1, casella2, casella3, casella4, casella5, casella6, casella7, casella8, casella9, casella10,
            casella11, casella12, casella13, casella14, casella15, casella16, casella17, casella18, casella19, casella20,
            casella21, casella22, casella23, casella24, casella25, casella26, casella27, casella28, casella29, casella30,
            casella31, casella32, casella33, casella34, casella35, casella36;

    @FXML
    public ImageView imgJugadorActual;

    @FXML
    private Label titol1;

    @FXML
    private GridPane gridJugadors;

    //botons de les cartes, utilit??ats per comprar les empreses
    @FXML
    private Button botonCarta1Empresa, botonCarta2Empresa, botonCarta3Empresa, botonCarta4Empresa, botonCarta5Empresa, botonCarta6Empresa;

    //botons de les cartes, utilit??ats per pujar les accions de les empreses
    @FXML
    private Button botonColorCarta1Empresa1, botonColorCarta1Empresa2, botonColorCarta2Empresa1, botonColorCarta2Empresa2,
            botonColorCarta3Empresa1, botonColorCarta3Empresa2, botonColorCarta4Empresa1, botonColorCarta4Empresa2,
            botonColorCarta5Empresa1, botonColorCarta5Empresa2, botonColorCarta6Empresa1, botonColorCarta6Empresa2;

    //Imatges que tindr??n les funcions de les cartes
    @FXML
    private ImageView imagenEmpresa1, imagenEmpresa2, imagenEmpresa3, imagenEmpresa4, imagenEmpresa5, imagenEmpresa6;

    //Imatges que tindr??n les funcions de les cartes
    @FXML
    private ImageView imagenColor1Carta1, imagenColor2Carta1, imagenColor1Carta2, imagenColor2Carta2, imagenColor1Carta3, imagenColor2Carta3,
            imagenColor1Carta4, imagenColor2Carta4, imagenColor1Carta5, imagenColor2Carta5, imagenColor1Carta6, imagenColor2Carta6;

    //Imatgeview amb l'imatge ja assignada
    @FXML
    public ImageView imatgeAssignada1Empresa1, imatgeAssignada1Empresa2, imatgeAssignada1Empresa3, imatgeAssignada1Empresa4, imatgeAssignada1Empresa5, imatgeAssignada1Empresa6;

    @FXML
    protected void passaTorn() throws SQLException, IOException {
        if(t.cartaColocada){
            t.nouTorn();
        }
    }

    @FXML
    protected void onDemoClick() throws SQLException, IOException {
        t.demo(this);
    }

    //Metode per desapareixer la carta1
    @FXML
    protected void onEmpresaCard1Click() {
        if (t.cartaColocada == false) {
//            esconderCarta(botonCarta1Empresa, botonColorCarta1Empresa1, botonColorCarta1Empresa2, imagenEmpresa1, imagenColor1Carta1, imagenColor2Carta1);
            t.imatgeActualEmpresa = imagenEmpresa1.getImage();
            t.jugadorActual.ma.remove(0);
            actualitzaCartes();
        }
    }

    //Metode per desapareixer la carta2
    @FXML
    protected void onEmpresaCard2Click() {
        if (t.cartaColocada == false) {
//            esconderCarta(botonCarta2Empresa, botonColorCarta2Empresa1, botonColorCarta2Empresa2, imagenEmpresa2, imagenColor1Carta2, imagenColor2Carta2);
            t.imatgeActualEmpresa = imagenEmpresa2.getImage();
            t.jugadorActual.ma.remove(1);
            actualitzaCartes();
        }
    }

    //Metode per desapareixer la carta3
    @FXML
    protected void onEmpresaCard3Click() {
        if (t.cartaColocada == false) {
//            esconderCarta(botonCarta3Empresa, botonColorCarta3Empresa1, botonColorCarta3Empresa2, imagenEmpresa3, imagenColor1Carta3, imagenColor2Carta3);
            t.imatgeActualEmpresa = imagenEmpresa3.getImage();
            t.jugadorActual.ma.remove(2);
            actualitzaCartes();
        }
    }

    //Metode per desapareixer la carta4
    @FXML
    protected void onEmpresaCard4Click() {
        if (t.cartaColocada == false) {
//            esconderCarta(botonCarta4Empresa, botonColorCarta4Empresa1, botonColorCarta4Empresa2, imagenEmpresa4, imagenColor1Carta4, imagenColor2Carta4);
            t.imatgeActualEmpresa = imagenEmpresa4.getImage();
            t.jugadorActual.ma.remove(3);
            actualitzaCartes();
        }
    }

    //Metode per desapareixer la carta5
    @FXML
    protected void onEmpresaCard5Click() {
        if (t.cartaColocada == false) {
//            esconderCarta(botonCarta5Empresa, botonColorCarta5Empresa1, botonColorCarta5Empresa2, imagenEmpresa5, imagenColor1Carta5, imagenColor2Carta5);
            t.imatgeActualEmpresa = imagenEmpresa5.getImage();
            t.jugadorActual.ma.remove(4);
            actualitzaCartes();
        }
    }

    //Metode per desapareixer la carta6
    @FXML
    protected void onEmpresaCard6Click() {
        if (t.cartaColocada == false) {
//            esconderCarta(botonCarta6Empresa, botonColorCarta6Empresa1, botonColorCarta6Empresa2, imagenEmpresa6, imagenColor1Carta6, imagenColor2Carta6);
            t.imatgeActualEmpresa = imagenEmpresa6.getImage();
            t.jugadorActual.ma.remove(5);
            actualitzaCartes();
        }
    }
    @FXML
    protected void onEmpresaCard1Color1Click() throws SQLException, IOException {
        if(t.cartaColocada==false){
            t.clickAument(imagenColor1Carta1);
            t.jugadorActual.ma.remove(0);
            actualitzaCartes();
        }
    }@FXML
    protected void onEmpresaCard1Color2Click() throws SQLException, IOException {
        if(t.cartaColocada==false){
            t.clickAument(imagenColor2Carta1);
            t.jugadorActual.ma.remove(0);
            actualitzaCartes();
        }
    }@FXML
    protected void onEmpresaCard2Color1Click() throws SQLException, IOException {
        if(t.cartaColocada==false){
            t.clickAument(imagenColor1Carta2);
            t.jugadorActual.ma.remove(1);
            actualitzaCartes();
        }
    }@FXML
    protected void onEmpresaCard2Color2Click() throws SQLException, IOException {
        if(t.cartaColocada==false){
            t.clickAument(imagenColor2Carta2);
            t.jugadorActual.ma.remove(1);
            actualitzaCartes();
        }
    }@FXML
    protected void onEmpresaCard3Color1Click() throws SQLException, IOException {
        if(t.cartaColocada==false){
            t.clickAument(imagenColor1Carta3);
            t.jugadorActual.ma.remove(2);
            actualitzaCartes();
        }
    }@FXML
    protected void onEmpresaCard3Color2Click() throws SQLException, IOException {
        if(t.cartaColocada==false){
            t.clickAument(imagenColor2Carta3);
            t.jugadorActual.ma.remove(2);
            actualitzaCartes();
        }
    }@FXML
    protected void onEmpresaCard4Color1Click() throws SQLException, IOException {
        if(t.cartaColocada==false){
            t.clickAument(imagenColor1Carta4);
            t.jugadorActual.ma.remove(3);
            actualitzaCartes();
        }
    }@FXML
    protected void onEmpresaCard4Color2Click() throws SQLException, IOException {
        if(t.cartaColocada==false){
            t.clickAument(imagenColor2Carta4);
            t.jugadorActual.ma.remove(3);
            actualitzaCartes();
        }
    }@FXML
    protected void onEmpresaCard5Color1Click() throws SQLException, IOException {
        if(t.cartaColocada==false){
            t.clickAument(imagenColor1Carta5);
            t.jugadorActual.ma.remove(4);
            actualitzaCartes();
        }
    }@FXML
    protected void onEmpresaCard5Color2Click() throws SQLException, IOException {
        if(t.cartaColocada==false){
            t.clickAument(imagenColor2Carta5);
            t.jugadorActual.ma.remove(4);
            actualitzaCartes();
        }
    }@FXML
    protected void onEmpresaCard6Color1Click() throws SQLException, IOException {
        if(t.cartaColocada==false){
            t.clickAument(imagenColor1Carta6);
            t.jugadorActual.ma.remove(5);
            actualitzaCartes();
        }
    }@FXML
    protected void onEmpresaCard6Color2Click() throws SQLException, IOException {
        if(t.cartaColocada==false){
            t.clickAument(imagenColor2Carta6);
            t.jugadorActual.ma.remove(5);
            actualitzaCartes();
        }
    }

    //Metodo para esconder las cartas
//    public void esconderCarta(Button cartaEmpresa, Button color1cartaEmpresa, Button color2cartaEmpresa, ImageView Empresa, ImageView colo1carta, ImageView color2carta) {
//        t.ReproducirSonido(sonidoCarta);
//        cartaEmpresa.setVisible(false);
//        color1cartaEmpresa.setVisible(false);
//        color2cartaEmpresa.setVisible(false);
//        Empresa.setImage(null);
//        colo1carta.setImage(null);
//        color2carta.setImage(null);
//    }

    public void mostrarNext() {
        next.setVisible(true);
    }

    @FXML
    public void onNextClick() throws SQLException, IOException {
        t.jugadorActual = t.jugadors.get(0);
        t.iniciarJoc();

        t.ReproducirSonido(sonidoClick);
        gridJugadors.setVisible(false);
        titol1.setText("TORN DEL JUGADOR NUMERO 1");
        t.jugadorActual = t.jugadors.get(0);
        imgJugadorActual.setImage(t.jugadors.get(0).pe??a);
        imgJugadorActual.setVisible(true);
        next.setVisible(false);

        t.insertarJugadoresBaseDatos();
        demoButton.setVisible(false);
    }

    public void setTitol1(String text) {
        titol1.setText(text);
    }


    @FXML
    public void pe??a1click() throws SQLException, IOException {
        if (IntrodueixNomUsuari) {
            if(this.nomJugador.getText().equals("")){

            }else{
                String nom = fiIntrodueixJugador(pe??a1);
                t.insertaJugador(pe??a1.getImage(), this, nom);
            }
        } else {
            t.ReproducirSonido(sonidoClick);
            introdueixJugador(pe??a1);

        }
    }

    @FXML
    public void pe??a2click() throws SQLException, IOException {
        if (IntrodueixNomUsuari) {
            if(this.nomJugador.getText().equals("")){

            }else{
            String nom = fiIntrodueixJugador(pe??a2);
            t.insertaJugador(pe??a2.getImage(), this, nom);}
        } else {
            t.ReproducirSonido(sonidoClick);
            introdueixJugador(pe??a2);
        }

    }

    @FXML
    public void pe??a3click() throws SQLException, IOException {
        if (IntrodueixNomUsuari) {
            if(this.nomJugador.getText().equals("")){

            }else{
            String nom = fiIntrodueixJugador(pe??a3);
            t.insertaJugador(pe??a2.getImage(), this, nom);}
        } else {
            t.ReproducirSonido(sonidoClick);
            introdueixJugador(pe??a3);
        }

    }

    @FXML
    public void pe??a4click() throws SQLException, IOException {

        if (IntrodueixNomUsuari) {
            if(this.nomJugador.getText().equals("")){

            }else{
            String nom = fiIntrodueixJugador(pe??a4);
            t.insertaJugador(pe??a2.getImage(), this, nom);}
        } else {
            t.ReproducirSonido(sonidoClick);
            introdueixJugador(pe??a4);
        }

    }

    @FXML
    public void pe??a5click() throws SQLException, IOException {
        if (IntrodueixNomUsuari) {
            if(this.nomJugador.getText().equals("")){

            }else{
            String nom = fiIntrodueixJugador(pe??a5);
            t.insertaJugador(pe??a2.getImage(), this, nom);}
        } else {

            t.ReproducirSonido(sonidoClick);
            introdueixJugador(pe??a5);
        }

    }

    @FXML
    public void pe??a6click() throws SQLException, IOException {
        if (IntrodueixNomUsuari) {
            if(this.nomJugador.getText().equals("")){
                System.out.println(nomJugador);
            }else{
                System.out.println(nomJugador);
            String nom = fiIntrodueixJugador(pe??a6);
            t.insertaJugador(pe??a2.getImage(), this, nom);}
        } else {
            t.ReproducirSonido(sonidoClick);
            introdueixJugador(pe??a6);
        }

    }

    public void introdueixJugador(ImageView pe??a) {
        next.setVisible(false);
        imageMemoria = pe??a.getImage();
        desactivaPe??es();
        pe??a.setDisable(false);
        pe??a.setImage(next.getImage());
        nomJugador.setVisible(true);
        IntrodueixNomUsuari = true;
    }

    public String fiIntrodueixJugador(ImageView pe??a) {
        pe??a.setImage(imageMemoria);
        IntrodueixNomUsuari = false;
        activaPe??es();
        pe??a.setVisible(false);
        String nom = nomJugador.getText();
        nomJugador.setText("");
        nomJugador.setVisible(false);
        if(t.jugadors.size()>=3){
            next.setVisible(true);
        }
        return nom;
    }

    public void activaPe??es() {
        pe??a1.setDisable(false);
        pe??a2.setDisable(false);
        pe??a3.setDisable(false);
        pe??a4.setDisable(false);
        pe??a5.setDisable(false);
        pe??a6.setDisable(false);
    }

    public void desactivaPe??es() {
        pe??a1.setDisable(true);
        pe??a2.setDisable(true);
        pe??a3.setDisable(true);
        pe??a4.setDisable(true);
        pe??a5.setDisable(true);
        pe??a6.setDisable(true);
    }


    @FXML
    protected void onCompraAlpha() throws SQLException, IOException {
        t.compraAccions("Alpha");

    }

    @FXML
    protected void onCompraBeta() throws SQLException, IOException {
        t.compraAccions("Beta");

    }

    @FXML
    protected void onCompraGamma() throws SQLException, IOException {
        t.compraAccions("Gamma");

    }

    @FXML
    protected void onCompraDelta() throws SQLException, IOException {
        t.compraAccions("Delta");

    }

    @FXML
    protected void onCompraEpsilon() throws SQLException, IOException {
        t.compraAccions("Epsilon");

    }

    @FXML
    protected void onCompraOmega() throws SQLException, IOException {
        t.compraAccions("Omega");

    }

    @FXML
    protected void onVenAlpha() throws SQLException, IOException {
        t.vendreAccions("Alpha");

    }

    @FXML
    protected void onVenBeta() throws SQLException, IOException {
        t.vendreAccions("Beta");

    }

    @FXML
    protected void onVenGamma() throws SQLException, IOException {
        t.vendreAccions("Gamma");

    }

    @FXML
    protected void onVenDelta() throws SQLException, IOException {
        t.vendreAccions("Delta");

    }

    @FXML
    protected void onVenEpsilon() throws SQLException, IOException {
        t.vendreAccions("Epsilon");

    }

    @FXML
    protected void onVenOmega() throws SQLException, IOException {
        t.vendreAccions("Omega");
    }

    public void cambiaMonedas(int monedas) {
        this.monedas.setText(String.valueOf(monedas));
    }

    //Seleccio del nou president
    public void seleccionaUnNouPresident(ArrayList posiblesPresidents, empresa empresaActual) {
        nomJugador.setText("Selecciona un nou president per " + empresaActual.nom);
        gridJugadors.setVisible(true);
        for (int x = 0; x < posiblesPresidents.size(); x++) {
            this.mostraJugador((jugador) posiblesPresidents.get(x));
        }
    }

    public void mostraJugador(jugador j) {
        System.out.println(j.nom);
    }

    //Caselles filials amb les cartes


    @FXML
    public void casella1Click() throws SQLException, IOException {
        casellaXclick(casella1);
    }

    @FXML
    public void casella2Click() throws SQLException, IOException {
        casellaXclick(casella2);
    }

    @FXML
    public void casella3Click() throws SQLException, IOException {
        casellaXclick(casella3);
    }

    @FXML
    public void casella4Click() throws SQLException, IOException {
        casellaXclick(casella4);
    }

    @FXML
    public void casella5Click() throws SQLException, IOException {
        casellaXclick(casella5);
    }

    @FXML
    public void casella6Click() throws SQLException, IOException {
        casellaXclick(casella6);
    }

    @FXML
    public void casella7Click() throws SQLException, IOException {
        casellaXclick(casella7);
    }

    @FXML
    public void casella8Click() throws SQLException, IOException {
        casellaXclick(casella8);
    }

    @FXML
    public void casella9Click() throws SQLException, IOException {
        casellaXclick(casella9);
    }

    @FXML
    public void casella10Click() throws SQLException, IOException {
        casellaXclick(casella10);
    }

    @FXML
    public void casella11Click() throws SQLException, IOException {
        casellaXclick(casella11);
    }

    @FXML
    public void casella12Click() throws SQLException, IOException {
        casellaXclick(casella12);
    }

    @FXML
    public void casella13Click() throws SQLException, IOException {
        casellaXclick(casella13);
    }

    @FXML
    public void casella14Click() throws SQLException, IOException {
        casellaXclick(casella14);
    }

    @FXML
    public void casella15Click() throws SQLException, IOException {
        casellaXclick(casella15);
    }

    @FXML
    public void casella16Click() throws SQLException, IOException {
        casellaXclick(casella16);
    }

    @FXML
    public void casella17Click() throws SQLException, IOException {
        casellaXclick(casella17);
    }

    @FXML
    public void casella18Click() throws SQLException, IOException {
        casellaXclick(casella18);
    }

    @FXML
    public void casella19Click() throws SQLException, IOException {
        casellaXclick(casella19);
    }

    @FXML
    public void casella20Click() throws SQLException, IOException {
        casellaXclick(casella20);
    }

    @FXML
    public void casella21Click() throws SQLException, IOException {
        casellaXclick(casella21);
    }

    @FXML
    public void casella22Click() throws SQLException, IOException {
        casellaXclick(casella22);
    }

    @FXML
    public void casella23Click() throws SQLException, IOException {
        casellaXclick(casella23);
    }

    @FXML
    public void casella24Click() throws SQLException, IOException {
        casellaXclick(casella24);
    }

    @FXML
    public void casella25Click() throws SQLException, IOException {
        casellaXclick(casella25);
    }

    @FXML
    public void casella26Click() throws SQLException, IOException {
        casellaXclick(casella26);
    }

    @FXML
    public void casella27Click() throws SQLException, IOException {
        casellaXclick(casella27);
    }

    @FXML
    public void casella28Click() throws SQLException, IOException {
        casellaXclick(casella28);
    }

    @FXML
    public void casella29Click() throws SQLException, IOException {
        casellaXclick(casella29);
    }

    @FXML
    public void casella30Click() throws SQLException, IOException {
        casellaXclick(casella30);
    }

    @FXML
    public void casella31Click() throws SQLException, IOException {
        casellaXclick(casella31);
    }

    @FXML
    public void casella32Click() throws SQLException, IOException {
        casellaXclick(casella32);
    }

    @FXML
    public void casella33Click() throws SQLException, IOException {
        casellaXclick(casella33);
    }

    @FXML
    public void casella34Click() throws SQLException, IOException {
        casellaXclick(casella34);
    }

    @FXML
    public void casella35Click() throws SQLException, IOException {
        casellaXclick(casella35);
    }

    @FXML
    public void casella36Click() throws SQLException, IOException {
        casellaXclick(casella36);
    }

    //Funcio per assignar les caselles de filial amb l'imatge de l'empressa
    public void casellaXclick(ImageView casellaX) throws SQLException, IOException {
        t.colocaCasella(casellaX);
    }

    public void ocultarCartes() {
        //Carta1
        botonCarta1Empresa.setVisible(false);
        botonColorCarta1Empresa1.setVisible(false);
        botonColorCarta1Empresa2.setVisible(false);
        imagenEmpresa1.setImage(null);
        imagenColor1Carta1.setImage(null);
        imagenColor2Carta1.setImage(null);
        //Carta2
        botonCarta2Empresa.setVisible(false);
        botonColorCarta2Empresa1.setVisible(false);
        botonColorCarta2Empresa2.setVisible(false);
        imagenEmpresa2.setImage(null);
        imagenColor1Carta2.setImage(null);
        imagenColor2Carta2.setImage(null);
        //Carta3
        botonCarta3Empresa.setVisible(false);
        botonColorCarta3Empresa1.setVisible(false);
        botonColorCarta3Empresa2.setVisible(false);
        imagenEmpresa3.setImage(null);
        imagenColor1Carta3.setImage(null);
        imagenColor2Carta3.setImage(null);
        //Carta4
        botonCarta4Empresa.setVisible(false);
        botonColorCarta4Empresa1.setVisible(false);
        botonColorCarta4Empresa2.setVisible(false);
        imagenEmpresa4.setImage(null);
        imagenColor1Carta4.setImage(null);
        imagenColor2Carta4.setImage(null);
        //Carta5
        botonCarta5Empresa.setVisible(false);
        botonColorCarta5Empresa1.setVisible(false);
        botonColorCarta5Empresa2.setVisible(false);
        imagenEmpresa5.setImage(null);
        imagenColor1Carta5.setImage(null);
        imagenColor2Carta5.setImage(null);
        //Carta6
        botonCarta6Empresa.setVisible(false);
        botonColorCarta6Empresa1.setVisible(false);
        botonColorCarta6Empresa2.setVisible(false);
        imagenEmpresa6.setImage(null);
        imagenColor1Carta6.setImage(null);
        imagenColor2Carta6.setImage(null);
    }

    public void actualitzaCartes() {
        //Carta1
        if (t.jugadorActual.ma.size()>=1){
        carta carta1 = t.jugadorActual.ma.get(0);
        botonCarta1Empresa.setVisible(true);
        botonColorCarta1Empresa1.setVisible(true);
        botonColorCarta1Empresa2.setVisible(true);
        imagenEmpresa1.setImage(carta1.empresa.carta);
        imagenColor1Carta1.setImage(t.traduirColor(carta1.color1));
        imagenColor2Carta1.setImage(t.traduirColor(carta1.color2));}else{
            botonCarta1Empresa.setVisible(false);
            botonColorCarta1Empresa1.setVisible(false);
            botonColorCarta1Empresa2.setVisible(false);
            imagenEmpresa1.setImage(null);
            imagenColor1Carta1.setImage(null);
            imagenColor2Carta1.setImage(null);
        }
        //Carta2
        if (t.jugadorActual.ma.size()>=2){
        carta Carta2 = t.jugadorActual.ma.get(1);
        botonCarta2Empresa.setVisible(true);
        botonColorCarta2Empresa1.setVisible(true);
        botonColorCarta2Empresa2.setVisible(true);
        imagenEmpresa2.setImage(Carta2.empresa.carta);
        imagenColor1Carta2.setImage(t.traduirColor(Carta2.color1));
        imagenColor2Carta2.setImage(t.traduirColor(Carta2.color2));}else{
            botonCarta2Empresa.setVisible(false);
            botonColorCarta2Empresa1.setVisible(false);
            botonColorCarta2Empresa2.setVisible(false);
            imagenEmpresa2.setImage(null);
            imagenColor1Carta2.setImage(null);
            imagenColor2Carta2.setImage(null);
        }
        //Carta3
        if (t.jugadorActual.ma.size()>=3){
        carta Carta3 = t.jugadorActual.ma.get(2);
        botonCarta3Empresa.setVisible(true);
        botonColorCarta3Empresa1.setVisible(true);
        botonColorCarta3Empresa2.setVisible(true);
        imagenEmpresa3.setImage(Carta3.empresa.carta);
        imagenColor1Carta3.setImage(t.traduirColor(Carta3.color1));
        imagenColor2Carta3.setImage(t.traduirColor(Carta3.color2));}else{
            botonCarta3Empresa.setVisible(false);
            botonColorCarta3Empresa1.setVisible(false);
            botonColorCarta3Empresa2.setVisible(false);
            imagenEmpresa3.setImage(null);
            imagenColor1Carta3.setImage(null);
            imagenColor2Carta3.setImage(null);
        }
        //Carta
        if (t.jugadorActual.ma.size()>=4){
        carta Carta4 = t.jugadorActual.ma.get(3);
        botonCarta4Empresa.setVisible(true);
        botonColorCarta4Empresa1.setVisible(true);
        botonColorCarta4Empresa2.setVisible(true);
        imagenEmpresa4.setImage(Carta4.empresa.carta);
        imagenColor1Carta4.setImage(t.traduirColor(Carta4.color1));
        imagenColor2Carta4.setImage(t.traduirColor(Carta4.color2));}else{
            botonCarta4Empresa.setVisible(false);
            botonColorCarta4Empresa1.setVisible(false);
            botonColorCarta4Empresa2.setVisible(false);
            imagenEmpresa4.setImage(null);
            imagenColor1Carta4.setImage(t.traduirColor(null));
            imagenColor2Carta4.setImage(t.traduirColor(null));
        }
        //Carta5
        if (t.jugadorActual.ma.size()>=5){
        carta Carta5 = t.jugadorActual.ma.get(4);
        botonCarta5Empresa.setVisible(true);
        botonColorCarta5Empresa1.setVisible(true);
        botonColorCarta5Empresa2.setVisible(true);
        imagenEmpresa5.setImage(Carta5.empresa.carta);
        imagenColor1Carta5.setImage(t.traduirColor(Carta5.color1));
        imagenColor2Carta5.setImage(t.traduirColor(Carta5.color2));}else{
            botonCarta5Empresa.setVisible(false);
            botonColorCarta5Empresa1.setVisible(false);
            botonColorCarta5Empresa2.setVisible(false);
            imagenEmpresa5.setImage(null);
            imagenColor1Carta5.setImage(t.traduirColor(null));
            imagenColor2Carta5.setImage(t.traduirColor(null));
        }
        //Carta6
        if (t.jugadorActual.ma.size()==6){
            carta Carta6 = t.jugadorActual.ma.get(5);
            botonCarta6Empresa.setVisible(true);
            botonColorCarta6Empresa1.setVisible(true);
            botonColorCarta6Empresa2.setVisible(true);
            imagenEmpresa6.setImage(Carta6.empresa.carta);
            imagenColor1Carta6.setImage(t.traduirColor(Carta6.color1));
            imagenColor2Carta6.setImage(t.traduirColor(Carta6.color2));
        }else{
            botonCarta6Empresa.setVisible(false);
            botonColorCarta6Empresa1.setVisible(false);
            botonColorCarta6Empresa2.setVisible(false);
            imagenEmpresa6.setImage(null);
            imagenColor1Carta6.setImage(t.traduirColor(null));
            imagenColor2Carta6.setImage(t.traduirColor(null));}
    }
}