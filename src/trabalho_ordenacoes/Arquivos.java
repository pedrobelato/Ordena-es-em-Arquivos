package trabalho_ordenacoes;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;

public class Arquivos {
    private String nomearquivo;
    private RandomAccessFile arquivo;
    private final int TF = 512;
    private int comp=0, mov=0;

    public int getComp() {
        return comp;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }

    public int getMov() {
        return mov;
    }

    public void setMov(int mov) {
        this.mov = mov;
    }
    
    public int getTF(){
        return TF;
    }

    public Arquivos(String nomearquivo) {
        try
        {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e)
        {
        }
    }

    public void truncate(int pos) //desloca eof
    {
        try
        {
            arquivo.setLength(pos * Registro.length());
        } catch (IOException exc)
        {
        }
    }

    public boolean eof() {
        boolean retorno = false;
        try
        {
            if (arquivo.getFilePointer() == arquivo.length())
            {
                retorno = true;
            }
        } catch (IOException e)
        {
        }
        return (retorno);
    }

    public void inserirRegNoFinal(Registro reg) {
        seekArq(filesize());//ultimo byte
        reg.gravaNoArq(arquivo);
    }

    public void seekArq(int pos) {
        try
        {
            arquivo.seek(pos * Registro.length());
        } catch (IOException e)
        {
        }
    }
    
    public int filesize() {
        try
        {
            return (int) arquivo.length() / Registro.length();
        } catch (IOException e)
        {
            return 0;
        }
    }
    
    public void gerarArquivoOrdenado() throws IOException{
        for(int i = 0; i < TF; i++)
            this.inserirRegNoFinal(new Registro(i));
    }
    
    public void gerarArquivoReverso() throws IOException{
        for(int i = TF; i > 0; i--)
            this.inserirRegNoFinal(new Registro(i));
    }
    
    public void gerarArquivoRandomico() throws IOException{
        for(int i = 0; i < TF; i++)
            this.inserirRegNoFinal(new Registro((int)(Math.random()*1025)));
    }
    
    public int Maior(){
        Registro reg = new Registro();
        int maior, TL = filesize();
        seekArq(0);
        reg.leDoArq(arquivo);
        maior = reg.getCodigo();
        for(int i = 0; i < TL; i++)
        {
            reg.leDoArq(arquivo);
            if(reg.getCodigo()> maior)
                maior = reg.getCodigo();
        }
        return maior;
    }
    
    public int Menor(){
        Registro reg = new Registro();
        int menor, TL = filesize();
        this.seekArq(0);
        reg.leDoArq(arquivo);
        menor = reg.getCodigo();
        for(int i = 1; i < TL; i++)
        {
            reg.leDoArq(this.arquivo);
            if(reg.getCodigo() < menor)
                menor = reg.getCodigo();
        }
        return menor;
    }
    
    public void OrdInsercaoDireta()
    {
        OrdInsercaoDireta(0, TF);
    }
    
    public void OrdInsercaoDireta(int esq, int dir) {
        int pos;
        Registro reg = new Registro(), reg2 = new Registro();
        for (int i = esq+1; i < dir; i++)
        {
            pos = i;
            seekArq(pos-1);
            reg2.leDoArq(arquivo);
            reg.leDoArq(arquivo);
            comp++;
            while (pos > esq && reg.getCodigo() < reg2.getCodigo())
            {
                seekArq(pos);
                reg2.gravaNoArq(arquivo);
                pos--;
                mov++;
                comp++;
                seekArq(pos-1);
                reg2.leDoArq(arquivo);
            }
            if(pos != i){
                seekArq(pos);
                reg.gravaNoArq(arquivo);
                mov++;
            }
        }
    }
    
    private int busca_binaria_inserindo(int chave, int TL) {
        int inicio = 0, fim = TL - 1, meio = (inicio+fim) / 2;
        Registro reg = new Registro();
        seekArq(meio);
        reg.leDoArq(arquivo);
        while (inicio < fim)
        {
            comp++;
            if (chave > reg.getCodigo())
                inicio = meio + 1;
            else
                fim = meio - 1;
            meio = (inicio + fim) / 2;
            seekArq(meio);
            reg.leDoArq(arquivo);
        }
        comp++;
        if (chave > reg.getCodigo())
            return meio + 1;
        return meio;
    }
    
    public void OrdInsercaoBinaria(){
        int i, pos, TL = filesize();
        Registro regi = new Registro();
        Registro regj = new Registro();
        for (i = 1; i < TL; i++)
        {
            seekArq(i);
            regi.leDoArq(arquivo);
            pos = busca_binaria_inserindo(regi.getCodigo(), i);
            for (int j = i; j > pos;)
            {
                seekArq(j-1);
                regj.leDoArq(arquivo);
                regj.gravaNoArq(arquivo);
                mov++;
                j--;
            }
            if(pos != i)
            {
                seekArq(pos);
                regi.gravaNoArq(arquivo);
                mov++;
            }
        }
    }
    
    public void OrdSelecaoDireta(){
        int menor, posmenor, TL = filesize();
        Registro regi = new Registro();
        Registro regj = new Registro();
        for(int i = 0; i < TL - 1; i++)
        {
            seekArq(i);
            regi.leDoArq(arquivo);
            menor = regi.getCodigo();
            posmenor = i;
            for (int j = i + 1; j < TL;)
            {
                seekArq(j);
                regj.leDoArq(arquivo);
                comp++;
                if (regj.getCodigo() < menor)
                {
                    menor = regj.getCodigo();
                    posmenor = j;
                }
                j++;
            }
            seekArq(posmenor);
            regi.gravaNoArq(arquivo);
            seekArq(i);
            regj.setCodigo(menor);
            regj.gravaNoArq(arquivo);
            mov+=2;
        }
    }

    public void OrdBolha() {
        int TL2 = filesize();
        Registro aux = new Registro();
        Registro reg = new Registro();
        while (TL2 > 1)
        {
            for (int i = 0; i < TL2 - 1; i++)
            {
                seekArq(i);
                aux.leDoArq(arquivo);
                reg.leDoArq(arquivo);
                comp++;
                if (aux.getCodigo() > reg.getCodigo())
                {
                    seekArq(i);
                    reg.gravaNoArq(arquivo);
                    aux.gravaNoArq(arquivo);
                    mov += 2;
                }
            }
            TL2--;
        }
    }

    public void OrdShake() {
        int inicio = 0, fim = filesize() - 1;
        Registro regi = new Registro();
        Registro regii = new Registro();
        while (inicio < fim)
        {
            comp++;
            //Ir do inicio pro final
            for (int i = 0; i < fim; i++)
            {
                seekArq(i);
                regi.leDoArq(arquivo);
                regii.leDoArq(arquivo);
                comp++;
                if (regi.getCodigo() > regii.getCodigo())
                {
                    mov+=2;
                    seekArq(i);
                    regii.gravaNoArq(arquivo);
                    regi.gravaNoArq(arquivo);
                }
            }
            fim--;
            //Ir do final para o inicio
            for (int i = fim; i > inicio; i--)
            {
                seekArq(i-1);
                regii.leDoArq(arquivo);
                regi.leDoArq(arquivo);
                comp++;
                if (regi.getCodigo() < regii.getCodigo())
                {
                    mov+=2;
                    seekArq(i-1);
                    regi.gravaNoArq(arquivo);
                    regii.gravaNoArq(arquivo);
                }
            }
            inicio++;
        }
    }

    public void OrdHeapSort() {
        int FE, FD, maiorF;
        Registro regPai = new Registro();
        Registro regFE = new Registro();
        Registro regFD = new Registro();
        Registro maior = new Registro();
        Registro x = new Registro();
        Registro regTL = new Registro();
        for (int TL = filesize(); TL > 1;)
        {
            for (int pai = TL / 2 - 1; pai >= 0;)
            {
                FE = pai * 2 + 1;
                FD = FE+1;
                seekArq(FE);
                regFE.leDoArq(arquivo);
                regFD.leDoArq(arquivo);
                comp+=2;
                if (FD < TL && regFD.getCodigo() > regFE.getCodigo())
                    maiorF = FD;
                else
                    maiorF = FE;
                seekArq(pai);
                regPai.leDoArq(arquivo);
                seekArq(maiorF);
                maior.leDoArq(arquivo);
                if (maior.getCodigo() > regPai.getCodigo())
                {
                    mov+=2;
                    seekArq(pai);
                    maior.gravaNoArq(arquivo);
                    seekArq(maiorF);
                    regPai.gravaNoArq(arquivo);
                }
                pai--;
            }
            seekArq(0);
            x.leDoArq(arquivo);
            seekArq(TL - 1);
            regTL.leDoArq(arquivo);

            seekArq(0);
            regTL.gravaNoArq(arquivo);
            seekArq(TL - 1);
            x.gravaNoArq(arquivo);
            mov+=2;
            TL--;
        }
    }
    
    public void copiarArq(Arquivos arq){
        Registro reg = new Registro();
        arq.setComp(0);
        arq.setMov(0);
        arq.truncate(0);
        seekArq(0);
        while(!eof()){
            reg.leDoArq(arquivo);
            reg.gravaNoArq(arq.arquivo);
        }
    }

    public void OrdShell() {
        int TL = filesize();
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        for (int dist = 4; dist > 0; dist = dist / 2)
        {
            int i = 0;
            while(i < dist)
            {
                int j = i;
                while (j + dist < TL)
                {
                    seekArq(j);
                    reg1.leDoArq(arquivo);
                    seekArq(j + dist);
                    reg2.leDoArq(arquivo);
                    comp++;
                    if (reg1.getCodigo() > reg2.getCodigo())
                    {
                        seekArq(j);
                        reg2.gravaNoArq(arquivo);
                        seekArq(j + dist);
                        reg1.gravaNoArq(arquivo);
                        int k = j;
                        comp+=2;
                        if (k - dist >= i)
                        {
                            seekArq(k);
                            reg1.leDoArq(arquivo);
                            seekArq(k - dist);
                            reg2.leDoArq(arquivo);
                            while (k - dist >= i && reg1.getCodigo() < reg2.getCodigo())
                            {
                                mov+=2;
                                seekArq(k);
                                reg2.gravaNoArq(arquivo);
                                seekArq(k - dist);
                                reg1.gravaNoArq(arquivo);

                                k -= dist;
                                comp+=2;
                                if (k - dist >= i)
                                {
                                    seekArq(k);
                                    reg1.gravaNoArq(arquivo);
                                    seekArq(k - dist);
                                    reg2.gravaNoArq(arquivo);
                                }
                            }
                        }
                    }
                    j += dist;
                }
                i++;
            }
        }
    }
    
    public void OrdQuick_S_P(){
        OrdQuickSP(0,filesize()-1);
    }
    
    private void OrdQuickSP(int inicio, int fim){
        int i = inicio, j = fim;
        Registro regi = new Registro();
        Registro regj = new Registro();
        while (i < j)
        {
            comp++;
            seekArq(i);
            regi.leDoArq(arquivo);
            seekArq(j);
            regj.leDoArq(arquivo);
            while(i < j && regi.getCodigo() <= regj.getCodigo())
            {
                i++;
                seekArq(i);
                regi.leDoArq(arquivo);
                comp++;
            }
            if (i != j)
            {
                mov+=2;
                seekArq(j);
                regi.gravaNoArq(arquivo);
                seekArq(i);
                regj.gravaNoArq(arquivo);
            }
            while(i < j && regj.getCodigo() >= regi.getCodigo())
            {
                j--;
                seekArq(j);
                regj.leDoArq(arquivo);
                comp++;
            }
            if (i != j)
            {
                mov+=2;
                seekArq(j);
                regi.gravaNoArq(arquivo);
                seekArq(i);
                regj.gravaNoArq(arquivo);
            }
        }
        if (inicio < i - 1)
           OrdQuickSP(inicio, i - 1);
        if (j + 1 < fim)
            OrdQuickSP(j + 1, fim);
    }
    
    public void OrdQuick_C_P(){
        OrdQuickCP(0,filesize()-1);
    }
    
    private void OrdQuickCP(int inicio, int fim){
        Registro regi = new Registro();
        Registro regj = new Registro();
        Registro pivo = new Registro();
        seekArq((inicio+fim)/2);
        pivo.leDoArq(arquivo);
        int i = inicio, j = fim;
        while (i < j)
        {
            comp++;
            seekArq(i);
            regi.leDoArq(arquivo);
            while(regi.getCodigo() < pivo.getCodigo()){
                i++;
                seekArq(i);
                regi.leDoArq(arquivo);
            }
            seekArq(j);
            regj.leDoArq(arquivo);
            comp++;
            while(regj.getCodigo() > pivo.getCodigo()){
                j--;
                seekArq(j);
                regj.leDoArq(arquivo);
            }
            if(i<=j)
            {
                mov+=2;
                seekArq(j);
                regi.gravaNoArq(arquivo);
                seekArq(i);
                regj.gravaNoArq(arquivo);
                i++;
                j--;
            }
        }
        if (inicio < j)
            OrdQuickCP(inicio, j);
        if (i < fim)
            OrdQuickCP(i, fim);
    }
    
    public void MergeSort() throws IOException{
        int TL = filesize();
        int seq = 1, tam_seq, i, j, k, meio = filesize()/2;
        Arquivos arq1 = new Arquivos("v1");
        Arquivos arq2 = new Arquivos("v2");
        Registro reg = new Registro();
        Registro reg2 = new Registro();
        
        // gravar os numeros nos vetores auxiliares;
        while(seq < TL)
        {
            comp++;
            arq1.truncate(0);
            arq2.truncate(0);
            for(i=0; i<meio; i++)
            {
                seekArq(i);
                reg.leDoArq(arquivo);
                arq1.seekArq(i);
                reg.gravaNoArq(arq1.arquivo);
                
                seekArq(meio+i);
                reg2.leDoArq(arquivo);
                arq2.seekArq(i);
                reg2.gravaNoArq(arq2.arquivo);
                mov+=2;
            }
            i=j=k=0;
            tam_seq = seq;
            comp++;
            while(k<TL)
            {
                // controlar a sequencia
                seekArq(k);
                while(i<tam_seq && j<tam_seq)
                {
                    arq1.seekArq(i);
                    reg.leDoArq(arq1.arquivo);
                    arq2.seekArq(j);
                    reg2.leDoArq(arq2.arquivo);
                    comp+=3;
                    if(reg.getCodigo() < reg2.getCodigo())
                    {
                        reg.gravaNoArq(arquivo);
                        i++;
                    }
                    else
                    {
                        reg2.gravaNoArq(arquivo);
                        j++;
                    }
                    mov++;
                    k++;
                }
                comp++;
                while(i<tam_seq)
                {
                    comp++;
                    arq1.seekArq(i++);
                    reg.leDoArq(arq1.arquivo);
                    reg.gravaNoArq(arquivo);
                    mov++;
                    k++;
                }
                comp++;
                while(j<tam_seq)
                {
                    comp++;
                    arq2.seekArq(j++);
                    reg2.leDoArq(arq2.arquivo);
                    reg2.gravaNoArq(arquivo);
                    mov++;
                    k++;
                }
                tam_seq += seq;
            }
            seq *= 2;
        }
        arq1.arquivo.close();
        arq2.arquivo.close();
        File fecharv1 = new File("v1");
        fecharv1.delete();
        File fecharv2 = new File("v2");
        fecharv2.delete();
    }
    
    public void OrdMerge2() throws IOException{
        Arquivos arq = new Arquivos("Auxx");
        OrdMerge2(0, (int)filesize()-1, arq);
        arq.arquivo.close();
        File fechar = new File("Auxx");
        fechar.delete();
    }
    
    private void OrdMerge2(int esq, int dir, Arquivos arq){
        comp++;
        if(esq<dir)
        {
            int meio = (esq+dir)/2;
            OrdMerge2(esq, meio, arq);
            OrdMerge2(meio+1, dir, arq);
            fusao(esq,meio,meio+1,dir,arq);
        }
    }
    
    private void fusao(int inicio1, int fim1, int inicio2, int fim2, Arquivos arq){
        int i = inicio1, j = inicio2;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        arq.seekArq(0);
        arq.truncate(0);
        comp+=2;
        while(i<=fim1 && j<=fim2)
        {
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);
            comp+=3;
            if(reg1.getCodigo() < reg2.getCodigo())
            {
                reg1.gravaNoArq(arq.arquivo);
                i++;
            }
            else
            {
                reg2.gravaNoArq(arq.arquivo);
                j++;
            }
            mov++;
        }
        comp++;
        while(i<=fim1)
        {
            comp++;
            seekArq(i++);
            reg1.leDoArq(arquivo);
            reg1.gravaNoArq(arq.arquivo);
            mov++;
        }
        comp++;
        while(j<=fim2)
        {
            comp++;
            seekArq(j++);
            reg2.leDoArq(arquivo);
            reg2.gravaNoArq(arq.arquivo);
            mov++;
        }
        comp++;
        for(i=0; i<arq.filesize(); i++)
        {
            comp++;
            arq.seekArq(i);
            reg1.leDoArq(arq.arquivo);
            seekArq(inicio1+i);
            reg1.gravaNoArq(arquivo);
            mov++;
        }
    }
    
    public void OrdCountingSort() throws IOException{
        int TL = filesize();
        int maior = Maior();
        int vet[] = new int[maior+1];
        Registro reg = new Registro();
        Arquivos a = new Arquivos("Auxiliar");
        copiarArq(a);
        for (int i = 0; i < TL; i++)
        {
            comp++;
            a.seekArq(i);
            reg.leDoArq(a.arquivo);
            vet[reg.getCodigo()]++;
        }
        for (int i = 0; i < maior; i++)
            vet[i+1] = vet[i] + vet[i+1];
        
        for (int i = 0; i < TL; i++)
        {
            comp++;
            mov++;
            a.seekArq(i);
            reg.leDoArq(a.arquivo);
            seekArq(vet[reg.getCodigo()]-1);
            reg.gravaNoArq(arquivo);
        }
        a.arquivo.close();
        File fechar = new File("Auxiliar");
        fechar.delete();
    }
    
    public void OrdBuketSort(int qtde){
        Registro reg = new Registro();
        int maior = Maior(), menor = Menor(), TL = filesize();
        int dist = ((maior - menor)/qtde)+1;
        Lista ListaPonteiros[] = new Lista[qtde];
        for(int i=0; i < qtde; i++)
            ListaPonteiros[i] = new Lista();
        for(int i=0; i < TL; i++)
        {
            mov++;
            seekArq(i);
            reg.leDoArq(arquivo);
            ListaPonteiros[(reg.getCodigo()-menor)/dist].inserirNoFinal(reg.getCodigo());
        }
        
        seekArq(0);
        for(int i=0; i < qtde; i++)
        {
            comp++;
            if(ListaPonteiros[i] != null)
            {
                ListaPonteiros[i].Ord_Bolha();
                while(!ListaPonteiros[i].vazio())
                {
                    reg.setCodigo(ListaPonteiros[i].popCodigo());
                    reg.gravaNoArq(arquivo);
                    mov++;
                }
            }
        }
    }
    
    public void OrdRadixSort() throws IOException
    {
        int maior = Maior(), TL = filesize();
        int x = 1;
        Registro reg = new Registro();
        Lista ListaPonteiros[] = new Lista[10];
        
        for(int i=0; i < 10; i++)
            ListaPonteiros[i] = new Lista();
        
        while(maior/x > 0)
        {
            comp++;
            for(int i=0; i < TL; i++)
            {
                comp++;
                seekArq(i);
                reg.leDoArq(arquivo);
                ListaPonteiros[reg.getCodigo() / x % 10].inserirNoFinal(reg.getCodigo());
                mov++;
            }
            
            seekArq(0);
            for(int i=0; i < 10; i++)
            {
                comp++;
                while(!ListaPonteiros[i].vazio())
                {
                    reg.setCodigo(ListaPonteiros[i].popCodigo());
                    reg.gravaNoArq(arquivo);
                    mov++;
                }
            }
            x *= 10;
        }
    }
    
    public void OrdCombSort()
    {
        int dist, TL;
        dist = TL = filesize();
        Registro reg = new Registro();
        Registro reg2 = new Registro();
        comp++;
        while(dist > 0)
        {
            comp+=2;
            dist /= 1.3;
            for(int i=0; i < TL - dist; i++)
            {
                seekArq(i);
                reg.leDoArq(arquivo);
                seekArq(i + dist);
                reg2.leDoArq(arquivo);
                comp+=2;
                if(reg.getCodigo() > reg2.getCodigo())
                {
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    seekArq(i + dist);
                    reg.gravaNoArq(arquivo);
                    comp++;
                    mov+=2;
                }
            }
        }
    }
    
    public void OrdGnomeSort()
    {
        int TL = filesize(), j;
        Registro reg = new Registro();
        Registro reg2 = new Registro();
        boolean flag;
        comp++;
        for(int i=1; i < TL; i++)
        {
            comp+=3;
            seekArq(i);
            reg.leDoArq(arquivo);
            seekArq(i-1);
            reg2.leDoArq(arquivo);
            if(reg.getCodigo() < reg2.getCodigo())
            {
                comp+=2;
                seekArq(i);
                reg2.gravaNoArq(arquivo);
                seekArq(i-1);
                reg.gravaNoArq(arquivo);
                mov+=2;
                flag = true;
                j = i-1;
                comp++;
                while(j > 0 && flag)
                {
                    seekArq(j);
                    reg.leDoArq(arquivo);
                    seekArq(j-1);
                    reg2.leDoArq(arquivo);
                    comp++;
                    if(reg.getCodigo() < reg2.getCodigo())
                    {
                        seekArq(j);
                        reg2.gravaNoArq(arquivo);
                        seekArq(j-1);
                        reg.gravaNoArq(arquivo);
                        mov++;
                    }
                    else
                        flag = false;
                    j--;
                }
            }
        }
    }
    
    public void OrdTimSort() throws IOException
    {
        Arquivos Aux = new Arquivos("ArqAux");
        int dist = 32, TL = filesize();
        for(int i=0; i < TL; i+=dist)
            OrdInsercaoDireta(i, Math.min(i+dist, TL)); //mínimo número entre dois intervalos
        while(dist < TL)
        {
            for(int i=0; i < TL; i+= dist*2)
                fusao(i, i+dist-1, i+dist, Math.min(i+dist*2-1, TL-1), Aux);
            dist *= 2;
        }
        Aux.arquivo.close();
        File fechar = new File("ArqAux");
        fechar.delete();
    }
}