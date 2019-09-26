/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication11;

import WebServiceTarea2.model.ActividadesDto;
import WebServiceTarea2.model.ProyectoDto;
import WebServiceTarea2.model.SeguimientoDto;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.PasswordAuthentication;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Bran
 */
public class Correos {

    private String correo; //correo de donde se envia
    private String password; //password del correo que se envia
    private String destino; //Email donde se va enviar
    private String motivo; //motivo del email
    private String imagenWeb; //imagen del baner
    private String html;
    Properties propiedades = new Properties();

    public void sendActivationMail() { //link de activacion
        try {

//          String myCuenta = "sistemacontrolroles@gmail.com";
//          String pass = "programacion3";
            Session session = Session.getDefaultInstance(propiedades);
            Message mensage = new MimeMessage(session);
            InternetAddress from = new InternetAddress(correo, "");
            InternetAddress to = new InternetAddress(destino);
            mensage.setRecipient(Message.RecipientType.TO, to);
            mensage.setSubject(motivo); //se tiene que setear
            mensage.setContent(html, "text/html");
            Transport transport = session.getTransport("smtp");
            transport.connect(correo, password);
            transport.sendMessage(mensage, mensage.getRecipients(Message.RecipientType.TO));
            transport.close();

        } catch (AddressException | UnsupportedEncodingException ex) {
            Logger.getLogger(Correos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Correos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void enviarNotificacion(String destino, String asunto, String mensaje) {
        try {
            Session session = Session.getDefaultInstance(propiedades);
            Message mensage = new MimeMessage(session);
            InternetAddress from = new InternetAddress(correo, "");
            InternetAddress to = new InternetAddress(destino);
            mensage.setRecipient(Message.RecipientType.TO, to);
            mensage.setSubject(asunto);
            mensage.setContent("PlantillaCorreo.html", "text/html");
            Transport transport = session.getTransport("smtp");
            transport.connect(correo, password);
            transport.sendMessage(mensage, mensage.getRecipients(Message.RecipientType.TO));
            transport.close();

        } catch (UnsupportedEncodingException | MessagingException ex) {
            Logger.getLogger(Correos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Correos(String correo, String password, String motivo, String imagenWeb) {
        this.correo = correo;
        this.password = password;
        this.motivo = motivo;
        this.imagenWeb = imagenWeb;
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.debug", false);
        propiedades.put("mail.smtp.ssl.enable", "true");
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }

    public String getDestino() {
        return destino;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getImagenWeb() {
        return imagenWeb;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setImagenWeb(String imagenWeb) {
        this.imagenWeb = imagenWeb;
    }

    public Correos() {
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void cargarHtml() {
        try {
            FileReader filereader = new FileReader("testeo.html");
            BufferedReader br = new BufferedReader(filereader);
            String temp;
            while ((temp = br.readLine()) != null) {
                html = html + temp;
            }
            br.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

    } //metodo para mandar codigos de activacion

    public void setDatosHtmlActivacion(String nombre, String link) {
        this.html = html.replace("VarNombre", nombre);
        this.html = html.replace("VarLink", link);
        System.out.println(html);
    }

    //metodo para setear datos para notificar
    public void setDatosHtmlNotificacion(String nombre, String notificacion) {
        this.html = html.replace("VarNombre", nombre);
        this.html = html.replace("VarTexto", notificacion);
    } //para notificar de los seguimientos

    public void setDatosHtmlSeguimiento(String nombre, String seguimiento, String proyecto, String fecha) {
        this.html = html.replace("VarNombre", nombre);
        this.html = html.replace("VarTexto", " la fecha " + fecha + " Al proyecto: " + proyecto
                + " <br/> Se le agrego un seguimiento  y cuenta con un avanse del :" + seguimiento + "%");
    }

    //metodo para enviar el estado total del proyecto
    //Obtiene losdatos de un map
    //roll= usuario,tecnico, patrocinador
    public void setDatosEstadoProyecto(ProyectoDto proyectodto, String roll, String cantSeguimientos, SeguimientoDto seguimientoDto,List<ActividadesDto> actividadesDto) {
        String destinatario = "";
        if (roll.equals("usuario")) {
            destinatario = proyectodto.getProLiderusuario();
        } else if (roll.equals("tecnico")) {
            destinatario = proyectodto.getProLidertecnico();
        } else if (roll.equals("patrocinador")) {
            destinatario = proyectodto.getProPatrocinador();
        }
        this.html = html.replace("VarNombre", destinatario);
        this.html = html.replace("VarTexto",
                "Se ha modificado  la informacion del proyecto " + proyectodto.getProNombre() + " por lo cual <br/>"
                + "el estado actual es el siguiente : <b/>"
                + "Nombre del Proyecto: " + proyectodto.getProNombre() + "<b/>"
                + "Estado :" + proyectodto.getProEstado() + "<b/>"
                + "Patrocinador: " + proyectodto.getProPatrocinador() + "<b/>"
                + "Lider Usuario: " + proyectodto.getProLiderusuario() + "<b/>"
                + "Lider Tecnico: " + proyectodto.getProLidertecnico() + "<b/>"
                + "Fecha de inicio planeada: " + proyectodto.getProFechainicio() + "<b/>"
                + "Fecha de inicio efectiva: " + proyectodto.getProFechainireal() + "<b/>"
                + "Fecha de finalizacion planeada: " + proyectodto.getProFechafinal() + "<b/>"
                + "Fecha de finalizacion efectiva: " + proyectodto.getProFechafinreal() + "<b/>"
                + "Cantidad de seguimientos totales: " + cantSeguimientos + "<b/>"
                + "Fecha del ultimo seguimiento: " + seguimientoDto.getFecha() + "<b/>"
                + "Porcentage del avanse: " + seguimientoDto.getAvance() + "%" + "<b/>"
                + "Actividades" + "<b/>"
                + "VarActividades"
        );
        String temporal= "";
        //los ordeno
        List<ActividadesDto> temp = actividadesDto.stream().sorted(Comparator.comparing(x->x.getOrden())).collect(Collectors.toList());
        for(ActividadesDto x:temp){
            temporal = temporal + x.getOrden().toString()+"-"+x.getDescripcion()+" <b/>";
        }
        this.html = html.replace("VarActividades",temporal);
    }
    public void setDatosNuevaActividades(ActividadesDto actividadesDto,String nombre){
        this.html = html.replace("VarNombre", nombre);
        this.html = html.replace("VarTexto","Se te a asignado una nueva actividad <b/>"+
                "Consiste en: "+actividadesDto.getDescripcion()+"<b/>"+
               "Se espera iniciar: "+actividadesDto.getInicioEsperado()+
                "Se espera que este terminada: "+actividadesDto.getFinalEsperado());

    }
    public void setDatosActualisacionActividad(ActividadesDto actividadesDto,String nombre){
          this.html = html.replace("VarNombre", nombre);
            this.html = html.replace("VarTexto","Se ha realizado una modificacion a la actividad : <b/>"+
                   actividadesDto.getDescripcion()+"<b/>"+
                    "Estado de La Actividad: <b/>"+
                    "descripcion: "+actividadesDto.getDescripcion()+"<b/>"+
                    "Inicio esperado: "+actividadesDto.getInicioEsperado()+"<b/>"+
                    "Final esperado: "+actividadesDto.getInicioEsperado()+"<b/>"+
                    "Inicio efectivo: "+actividadesDto.getInicioReal()+"<b/>"+
                    "Final efectivo :"+ actividadesDto.getFinalReal()+"<b/>"+
                    "Estado: "+actividadesDto.getEstado()+"<b/>"+
                    "Orden: "+actividadesDto.getOrden()+"<b/>"
                    );
    }
}
