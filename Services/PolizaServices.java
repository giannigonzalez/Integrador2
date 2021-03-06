package Services;
import Classes.*;

import java.util.*;

public class PolizaServices{
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    public void crearPoliza(Polizas pp, Clientes cc, Vehiculos vv){
        Poliza p = new Poliza();
        this.poliza(pp,p);
        this.clientes(p,cc);
        this.vehiculo(p,vv);
        this.inicioPoliza(p);
        this.finPoliza(p);
        this.cantidadCuotas(p);
        this.formaDePago(p);
        this.montoTotalAsegurado(p);
        this.incluyeGranizo(p);
        this.tipoCobertura(p);
        pp.getPolizas().put(p.getNumeroPoliza(),p);
    }

    public void modificarPoliza(Polizas pp,Clientes cc, Vehiculos vv){
        System.out.println("Ingrese número de póliza");
        int a=inputPoliza();
        if(this.polizaOK(pp,a))this.modificacion(pp, a, cc, vv);
        else System.out.println("Póliza inexistente");
    }

    public void eliminarPoliza(Polizas pp){
        System.out.println("Ingrese número de póliza");
        int a=inputPoliza();
        if(this.polizaOK(pp,a)){ 
            pp.getPolizas().remove(a);
            System.out.println("Póliza eliminada");
        }else{ System.out.println("Póliza inexistente");}
    }

    public void consultarPoliza(Polizas pp){
        System.out.println("Ingrese número de póliza");
        int a=inputPoliza();
        if(this.polizaOK(pp,a)) this.print(pp,a);
        else System.out.println("Póliza inexistente");
    }

    private void print(Polizas pp,int a){
        Poliza p = pp.getPolizas().get(a);
        System.out.println("Cliente Asegurado:");
        System.out.println("Nombre: "+p.getCliente().getNombre());
        System.out.println("Apellido: "+p.getCliente().getApellido());
        System.out.println("Documento: "+p.getCliente().getDocumento());
        System.out.println("Mail: "+p.getCliente().getMail());
        System.out.println("Teléfono: "+p.getCliente().getTelefono());
        System.out.println("Vehículo asegurado:");
        System.out.println("Patente: "+p.getVehiculo().getPatente());
        System.out.println("Marca: "+p.getVehiculo().getMarca());
        System.out.println("Modelo: "+p.getVehiculo().getModelo());
        System.out.println("Número de motor: "+p.getVehiculo().getNumeroMotor());
        System.out.println("Chasis: "+p.getVehiculo().getChasis());
        System.out.println("Color: "+p.getVehiculo().getColor());
        System.out.println("Tipo de vehículo: "+p.getVehiculo().getTipo());
        System.out.println("Póliza:");
        System.out.println("Número de póliza: "+p.getNumeroPoliza());
        System.out.println("Inicio de póliza: "+p.getInicioPoliza().get(Calendar.MONTH)+"/"+p.getInicioPoliza().get(Calendar.YEAR));
        System.out.println("Fin de póliza: "+p.getFinPoliza().get(Calendar.MONTH)+"/"+p.getFinPoliza().get(Calendar.YEAR));
        System.out.println("Cantidad de cuotas: "+p.getCantidadCuotas());
        System.out.println("Forma de pago: "+p.getFormaDePago());
        System.out.println("Monto Total Asegurado: $"+p.getMontoTotalAsegurado());
        if(p.getCoberturaGranizo())System.out.println("Cobertura contra granizo: sí");
        else System.out.println("Cobertura contra granizo: no");
        System.out.println("Tipo de cobertura: "+p.getTipoCobertura());
        p=null;
        
    }

    private int inputPoliza(){
        int a=0;
        try{
            a=leer.nextInt();
        }catch(Exception e){
            System.out.println("Hubo un error");
        }
        return a;
    }

    private boolean polizaOK(Polizas pp, int a){
        return pp.getPolizas().containsKey(a);
    }

    private void modificacion(Polizas pp,int a, Clientes cc, Vehiculos vv){
        Poliza p= pp.getPolizas().get(a);
        this.listaModificacion();
        int b=inputOpcionModificacion();
        while(b==0){b=inputOpcionModificacion();}
        this.switchModificacion(b, p,cc,vv);
        pp.getPolizas().put(a,p);
        p=null;
    }

    private void switchModificacion(int b,Poliza p,Clientes cc,Vehiculos vv){
        switch(b){
            case 1:this.clientes(p,cc);break;
            case 2:this.vehiculo(p,vv);break;
            case 3:this.inicioPoliza(p);break;
            case 4:this.finPoliza(p);break;
            case 5:this.cantidadCuotas(p);break;
            case 6:this.formaDePago(p);break;
            case 7:this.montoTotalAsegurado(p);break;
            case 8:this.incluyeGranizo(p);break;
            case 9:this.tipoCobertura(p);break;
        }
    }

    private int inputOpcionModificacion(){
        int a=0;
        try{
            a=modificacionOK(leer.nextInt());
        }catch(Exception e){
            System.out.println("Hubo un error");
        }
        return a;
    }

    private int modificacionOK(int a)throws MyException{
        if(a<1 || a>9) throw new MyException("Número fuera de rango");
        else return a;
    }

    private void listaModificacion(){
        System.out.println("1. Modificar cliente");
        System.out.println("2. Modificar vehículo");
        System.out.println("3. Modificar fecha de inicio");
        System.out.println("4. Modificar fecha de fin");
        System.out.println("5. Modificar cantidad de cuotas");
        System.out.println("6. Modificar forma de pago");
        System.out.println("7. Modificar monto total asegurado");
        System.out.println("8. Modificar cobertura contra granizo");
        System.out.println("9. Modificar tipo de cobertura");
    }

    private void poliza(Polizas pp, Poliza p){
        if(pp.getPolizas().isEmpty())p.setNumeroPoliza(1);
        else{
            int np = 1;
            while(pp.getPolizas().containsKey(np)){np++;}
            p.setNumeroPoliza(np);
        } 
    }

    private void clientes(Poliza p,Clientes cc){
        int dni=this.inputDNI();
        while(dni==0){dni=inputDNI();}
        if(cc.getClientes().containsKey(dni)){
            p.setCliente(cc.getClientes().get(dni));
            System.out.println("Cliente ingresado");
        }else{
            System.out.println("Se ingresará un nuevo cliente");
            ClienteServices cs = new ClienteServices();
            cs.crearCliente(cc);
            cs=null;
            this.clientes(p, cc);
        }
    }

    private int inputDNI(){
        int dni=0;
        System.out.println("Ingrese DNI");
        try{
            dni=dniOK(leer.nextInt());
        }catch(MyException me){
            System.out.println("Ha ocurrido un error");
        }catch(Exception e){
            System.out.println("Ha ocurrido un error");
        }
        return dni;
    }

    private int dniOK(int a) throws MyException{
        if(a<0 || a>99999999){ throw new MyException("Número inválido");}
        else{return a;}
    }

    private void vehiculo(Poliza p, Vehiculos vv){
        System.out.println("Ingrese patente");
        String patente = leer.next();
        if(patenteOK(vv, patente)){ 
            p.setVehiculo(vv.getVehiculos().get(patente));
            System.out.println("Vehículo agregado");
        }else{
            System.out.println("Deberá crear un nuevo vehículo");
            VehiculoServices vs = new VehiculoServices();
            vs.crearVehiculo(vv);
            vs=null;
            this.vehiculo(p, vv);
        }       
    }

    private boolean patenteOK(Vehiculos vv,String patente){
        return vv.getVehiculos().containsKey(patente);
    }

    private void inicioPoliza(Poliza p){
        int y=inputYear();
        while(y==0){y=inputYear();}
        int m=inputMonth();
        while(m==0){m=inputMonth();}
        GregorianCalendar g = new GregorianCalendar(y,m,1);
        p.setInicioPoliza(g);
    }

    private int inputYear(){
        int a=0;
        try{
            System.out.println("Ingrese año");
            a=añoOK(leer.nextInt());
        }catch(Exception e){
            System.out.println("Ha ocurrido un error");
        }
        return a;
    }

    private int añoOK(int a) throws MyException{
        if(a<1900) throw new MyException("Año anterior a 1900");
        else return a;
    }

    private int inputMonth(){
        int a=0;
        try{
            System.out.println("Ingrese numero");
            a=mesOK(leer.nextInt());
        }catch(Exception e){
            System.out.println("Ha ocurrido un error");
        }
        return a;
    }

    private int mesOK(int a) throws MyException{
        if(a>12 || a<1) throw new MyException("Mes fuera de rango");
        else return a;
    }

    private void finPoliza(Poliza p){
        int y=inputYear();
        while(y==0){y=inputYear();}
        int m=inputMonth();
        while(m==0){m=inputMonth();}
        GregorianCalendar g = new GregorianCalendar(y,m,1);
        if(g.get(Calendar.YEAR)>p.getInicioPoliza().get(Calendar.YEAR)){
            p.setFinPoliza(g);    
        }else if(g.get(Calendar.MONTH)>p.getInicioPoliza().get(Calendar.MONTH)){
            p.setFinPoliza(g);
        }else if(g.get(Calendar.DAY_OF_MONTH)>p.getInicioPoliza().get(Calendar.DAY_OF_MONTH)){
            p.setFinPoliza(g);
        }else{
            System.out.println("Fecha inválida, vuelva a ingresar fecha");
            this.finPoliza(p);
        }
    }
    
    private void cantidadCuotas(Poliza p){
        int cc= ingresarCuotas();
        while(cc==0){cc=ingresarCuotas();}
        p.setCantidadCuotas(cc);      
    }

    private int ingresarCuotas(){
        int a=0;
        System.out.println("Ingrese cantidad de cuotas");
        try{
            a=cuotasOK(leer.nextInt());
        }catch(Exception e){
            System.out.println("Ha ocurrido un error");
        }
        return a;
    }

    private int cuotasOK(int a)throws MyException{
        if(a<1) throw new MyException("Número negativo");
        else return a;
    }

    private void formaDePago(Poliza p){
        this.listaFormaDePago();       
        int a=ingresarForma();
        while(a==0){a=ingresarForma();}
        p.setFormaDePago(a);
    }

    private void listaFormaDePago(){
        System.out.println("1. Cheque");
        System.out.println("2. Debito Automático");
        System.out.println("3. Efectivo");
        System.out.println("4. Transferencia");       
    }

    private int ingresarForma(){
        int a=0;
        System.out.println("Ingrese cantidad de cuotas");
        try{
            a=formaOK(leer.nextInt());
        }catch(Exception e){
            System.out.println("Ha ocurrido un error");
        }
        return a;
    }

    private int formaOK(int a)throws MyException{
        if(a<1 || a>4) throw new MyException("Número fuera de rango");
        else return a;
    }
    
    private void montoTotalAsegurado(Poliza p){
        int a=ingresarMonto();
        while(a==0){a=ingresarMonto();}
        p.setMontoTotalAsegurado(a);       
    }

    private int ingresarMonto(){
        int a=0;
        System.out.println("Ingrese cantidad de cuotas");
        try{
            a=cuotasOK(leer.nextInt());
        }catch(Exception e){
            System.out.println("Ha ocurrido un error");
        }
        return a;
    }

    private void incluyeGranizo(Poliza p){
        int a=0;
        System.out.println("Ingrese 1 si incluye cobertura contra granizo");
        try{
            a=leer.nextInt();
        }catch(Exception e){
            System.out.println("Hubo un error");
        }finally{
            if(a==1) p.setCoberturaGranizo(true);
            else p.setCoberturaGranizo(false);
        }
    }

    private void tipoCobertura(Poliza p){
        this.listaTipoCobertura();
        int a=inputTipoCobertura();
        while(a==0){a=inputTipoCobertura();}
        p.setTipoCobertura(a);
    }

    private void listaTipoCobertura(){
        System.out.println("1. Total");
        System.out.println("2. Contra Terceros");
        System.out.println("3. Personal");
    }

    private int inputTipoCobertura(){
        int a=0;
        System.out.println("Ingrese código");
        try{
            a=tipoOK(leer.nextInt());
        }catch(Exception e){
            System.out.println("Ha ocurrido un error");
        }
        return a;
    }

    private int tipoOK(int a) throws MyException{
        if(a<1 || a>3) throw new MyException("Número fuera de rango");
        else return a;
    }
}