package trabalho_ordenacoes;

import java.io.IOException;
import java.io.RandomAccessFile;

class Registro {
    public final int tf=1022;
    private int codigo;
    private char lixo[] = new char[tf];
    
    public Registro(){}
    
    public Registro(int codigo){
        this.codigo = codigo;
        for(int i = 0; i < this.tf; i++)
            this.lixo[i] = 'X';
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public void gravaNoArq(RandomAccessFile arquivo){
        try{
            arquivo.writeInt(this.codigo);
            for(int i = 0; i < this.tf; i++)
                arquivo.writeChar(this.lixo[i]);
        }
        catch(IOException e){}
    }
    
    public void leDoArq(RandomAccessFile arquivo){
        try{
            this.codigo = arquivo.readInt();
            for(int i = 0; i < this.tf; i++)
                this.lixo[i]=arquivo.readChar();
        }
        catch(IOException e){}
    }
    
    public static int length(){
        return 2048;
    }
}
