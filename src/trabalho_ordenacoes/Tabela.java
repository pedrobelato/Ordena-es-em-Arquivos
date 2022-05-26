package trabalho_ordenacoes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Tabela {
    private final int dist = 44;
    private final int dist_menor = dist/5;
    private RandomAccessFile arqTabela;
    
    public Tabela(String nome, String nomeCol1, String nomeCol2, String nomeCol3)
    {
        try
        {
            nomeCol1 = nomeCol1;
            nomeCol2 = nomeCol2;
            nomeCol3 = nomeCol3;
            arqTabela = new RandomAccessFile(".\\" + nome + "", "rw");
            arqTabela.writeBytes(String.format("%-"+dist+"s|%-"+dist+"s|%-"+dist+"s|%-"+dist+"s|\n", "Métodos de Ordenação", nomeCol1, nomeCol2, nomeCol3));
            gerarLinhaTabela();
            arqTabela.writeBytes(String.format("%-"+dist+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|", "", "Comp.", "Comp.", "Mov.", "Mov.", "Tempo"));
            arqTabela.writeBytes(String.format("%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|", "Comp.", "Comp.", "Mov.", "Mov.", "Tempo"));
            arqTabela.writeBytes(String.format("%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|\n", "Comp.", "Comp.", "Mov.", "Mov.", "Tempo"));
            arqTabela.writeBytes(String.format("%-"+dist+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|", "", "Prog.*", "Equa.#", "Prog.+", "Equa.-", ""));
            arqTabela.writeBytes(String.format("%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|", "Prog.*", "Equa.#", "Prog.+", "Equa.-", ""));
            arqTabela.writeBytes(String.format("%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|%-"+dist_menor+"s|\n", "Prog.*", "Equa.#", "Prog.+", "Equa.-", ""));
            gerarLinhaTabela();
        }
        catch(FileNotFoundException e){
            System.out.println("Erro: Arquivo tabela!");
        }
        catch(IOException e){
            System.out.println("Erro: Arquivo tabela!");
        }
    }
    
    public void gerarLinhaTabela()
    {
        try
        {
            for(int i = 1; i < dist * 4 + 4; i++)
            {
                if(i%(dist+1) == 0)
                    arqTabela.writeBytes("|");
                else
                    arqTabela.writeBytes("-");
            }
            arqTabela.writeBytes("|\n");
        }
        catch(IOException e){
            System.out.println("Erro: Falha ao criar linha no arquivo!");
        }
    }
    
    public void inserirLinhaTabela(String nome_ord, int CPOrd, int CEOrd, int MPOrd, int MEOrd, long TOrd, int CPRev, int CERev, int MPRev, int MERev, long TRev, int CPRan, int CERan, int MPRan, int MERan, long TRan)
    {
        try{
            arqTabela.writeBytes(String.format("%-"+dist+"s|%-"+dist_menor+"d|%-"+dist_menor+"d|%-"+dist_menor+"d|%-"+dist_menor+"d|%-"+dist_menor+"d|" + "%-"+dist_menor+"d|%-"+dist_menor+"d|%-"+dist_menor+"d|%-"+dist_menor+"d|%-"+dist_menor+"d|" + "%-"+dist_menor+"d|%-"+dist_menor+"d|%-"+dist_menor+"d|%-"+dist_menor+"d|%-"+dist_menor+"d|\n", nome_ord, CPOrd, CEOrd, MPOrd, MEOrd, TOrd, CPRev, CERev, MPRev, MERev, TRev, CPRan, CERan, MPRan, MERan, TRan));
        }
        catch(IOException e){
            System.out.println("Deu erro na hora de criar a linha no arquivo tabela");
        }
    }
}
