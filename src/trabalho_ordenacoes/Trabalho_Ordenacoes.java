package trabalho_ordenacoes;

import java.io.IOException;

public class Trabalho_Ordenacoes {
    
    public static void gerarArq(Arquivos arqOrd, Arquivos arqRev, Arquivos arqRand)
    {
        try{
            arqOrd.gerarArquivoOrdenado();
        }catch(IOException e)
        {
            System.out.println("Erro no Arq Ordenado");
        }
        try{
            arqRev.gerarArquivoReverso();
        }catch(IOException e)
        {
            System.out.println("Erro no Arq Reverso");
        }
        try{
            arqRand.gerarArquivoRandomico();
        }catch(IOException e)
        {
            System.out.println("Erro no Arq Randomico");
        }
    }
    
    public static void gerarListas(Lista ListaOrd, Lista ListaRev, Lista ListaRand)
    {
        try{
            ListaOrd.gerarListaOrdenada();
        }catch(Exception e){
            System.out.println("Erro na lista Ordenada");
        }
        try{
            ListaRev.gerarListaReversa();
        }catch(Exception e){
            System.out.println("Erro na lista Reversa");
        }
        try{
            ListaRand.gerarListaRandomica();
        }catch(Exception e){
            System.out.println("Erro na lista Randomica");
        }
    }
    
    public static void OrdenarArquivos(Arquivos arqOrd, Arquivos arqRev, Arquivos arqRand, Tabela TAB) throws IOException
    {
        Arquivos AuxOrd = new Arquivos("AuxOrd.dat");
        Arquivos AuxRev = new Arquivos("AuxRev.dat");
        Arquivos AuxRand = new Arquivos("AuxRand.dat");
        long IniOrd, FimOrd, TotOrd, IniRev, FimRev, TotRev, IniRand, FimRand, TotRand;
        
        System.out.println("****** Inserção Direta *****");
        arqOrd.copiarArq(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdInsercaoDireta();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        arqRev.copiarArq(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdInsercaoDireta();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        arqRand.copiarArq(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdInsercaoDireta();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Inserção Direta",
                                AuxOrd.getComp(),
                                AuxOrd.getTF() - 1,
                                AuxOrd.getMov(),
                                3 * (AuxOrd.getTF() - 1),
                                TotOrd,
                                
                                AuxRev.getComp(),
                                ((int)Math.pow(AuxRev.getTF(), 2) + AuxRev.getTF() - 4) / 4,
                                AuxRev.getMov(),
                                ((int)Math.pow(AuxRev.getTF(), 2) + 3 * AuxRev.getTF() - 4) / 2,
                                TotRev,
                                
                                AuxRand.getComp(),
                                ((int)Math.pow(AuxRev.getTF(), 2) + AuxRev.getTF() - 2) / 4,
                                AuxRand.getMov(),
                                ((int)Math.pow(AuxRev.getTF(), 2) + 9 * AuxRev.getTF() - 10) / 4,
                                TotRand);
        
        System.out.println("***** Inserção Binária *****");
        arqOrd.copiarArq(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdInsercaoBinaria();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        arqRev.copiarArq(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdInsercaoBinaria();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        arqRand.copiarArq(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdInsercaoBinaria();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Inserção Binária",
                                AuxOrd.getComp(),
                                AuxRand.getTF() * (int)(Math.log10(AuxRand.getTF()) + Math.log10(Math.E)),
                                AuxOrd.getMov(),
                                3 * (AuxOrd.getTF() - 1),
                                TotOrd,
                                
                                AuxRev.getComp(),
                                AuxRand.getTF() * (int)(Math.log10(AuxRand.getTF()) + Math.log10(Math.E)),
                                AuxRev.getMov(),
                                ((int)Math.pow(AuxRev.getTF(), 2) + 3 * AuxRev.getTF() - 4) / 2,
                                TotRev,
                                
                                AuxRand.getComp(),
                                AuxRand.getTF() * (int)(Math.log10(AuxRand.getTF()) + Math.log10(Math.E)),
                                AuxRand.getMov(),
                                ((int)Math.pow(AuxRev.getTF(), 2) + 9 * AuxRev.getTF() - 10) / 4,
                                TotRand);
        
        System.out.println("***** Seleção Direta *****");
        arqOrd.copiarArq(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdSelecaoDireta();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        arqRev.copiarArq(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdSelecaoDireta();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        arqRand.copiarArq(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdSelecaoDireta();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Seleção Direta",
                                AuxOrd.getComp(),
                                (int)Math.pow(AuxOrd.getTF(), 2) - AuxOrd.getTF() / 2,
                                AuxOrd.getMov(),
                                3 * (AuxOrd.getTF() - 1),
                                TotOrd,
                                
                                AuxRev.getComp(),
                                (int)Math.pow(AuxRev.getTF(), 2) - AuxRev.getTF() / 2,
                                AuxRev.getMov(),
                                (int)Math.pow(AuxRev.getTF(), 2) / (4 + 3 * (AuxRev.getTF() - 1)),
                                TotRev,
                                
                                AuxRand.getComp(),
                                (int)Math.pow(AuxRand.getTF(), 2) - AuxRand.getTF() / 2,
                                AuxRand.getMov(),
                                AuxRand.getTF() * (int)(Math.log10(AuxRand.getTF()) + 0.5772),
                                TotRand);
        
        System.out.println("***** Ordenação Bolha *****");
        arqOrd.copiarArq(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdBolha();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        arqRev.copiarArq(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdBolha();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        arqRand.copiarArq(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdBolha();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Bolha",
                                AuxOrd.getComp(),
                                ((int)Math.pow(AuxRev.getTF(), 2) - AuxRev.getTF()) / 2,
                                AuxOrd.getMov(),
                                0,
                                TotOrd,
                                AuxRev.getComp(),
                                ((int)Math.pow(AuxRev.getTF(), 2) - AuxRev.getTF()) / 2,
                                AuxRev.getMov(),
                                (3 * ((int)Math.pow(AuxRev.getTF(), 2) - AuxRev.getTF())) / 2,
                                TotRev,
                                AuxRand.getComp(),
                                ((int)Math.pow(AuxRev.getTF(), 2) - AuxRev.getTF()) / 2,
                                AuxRand.getMov(),
                                (3 * ((int)Math.pow(AuxRev.getTF(), 2) - AuxRev.getTF())) / 4,
                                TotRand);
        
        System.out.println("***** Ordenação Bolha(Agitação) *****");
        arqOrd.copiarArq(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdShake();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        arqRev.copiarArq(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdShake();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        arqRand.copiarArq(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdShake();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Shake",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Shell *****");
        arqOrd.copiarArq(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdShell();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        arqRev.copiarArq(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdShell();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        arqRand.copiarArq(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdShell();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Shell",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Heap *****");
        arqOrd.copiarArq(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdHeapSort();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        arqRev.copiarArq(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdHeapSort();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        arqRand.copiarArq(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdHeapSort();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Heap",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Quick Sem Pivo *****");
        arqOrd.copiarArq(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdQuick_S_P();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        arqRev.copiarArq(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdQuick_S_P();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        arqRand.copiarArq(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdQuick_S_P();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Quick SP",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Quick Com Pivo *****");
        arqOrd.copiarArq(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdQuick_C_P();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        arqRev.copiarArq(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdQuick_C_P();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        arqRand.copiarArq(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdQuick_C_P();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Quick CP",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Merge 1 *****");
        arqOrd.copiarArq(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.MergeSort();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        arqRev.copiarArq(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.MergeSort();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        arqRand.copiarArq(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.MergeSort();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Merge 1",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Merge 2 *****");
        arqOrd.copiarArq(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdMerge2();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        arqRev.copiarArq(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdMerge2();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        arqRand.copiarArq(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdMerge2();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Merge 2",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Counting *****");
        arqOrd.copiarArq(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdCountingSort();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        arqRev.copiarArq(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdCountingSort();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        arqRand.copiarArq(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdCountingSort();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Counting",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Bucket *****");
        arqOrd.copiarArq(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdBuketSort(20);
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        arqRev.copiarArq(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdBuketSort(20);
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        arqRand.copiarArq(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdBuketSort(20);
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Bucket",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Radix *****");
        arqOrd.copiarArq(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdRadixSort();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        arqRev.copiarArq(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdRadixSort();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        arqRand.copiarArq(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdRadixSort();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Radix",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Comb *****");
        arqOrd.copiarArq(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdCombSort();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        arqRev.copiarArq(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdCombSort();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        arqRand.copiarArq(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdCombSort();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Comb",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Gnome *****");
        arqOrd.copiarArq(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdGnomeSort();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        arqRev.copiarArq(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdGnomeSort();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        arqRand.copiarArq(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdGnomeSort();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Gnome",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        
        System.out.println("***** Ordenação Tim *****");
        arqOrd.copiarArq(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdTimSort();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        arqRev.copiarArq(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdTimSort();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        arqRand.copiarArq(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdTimSort();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Tim",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
    }
    
    public static void OrdenarListas(Lista ListaOrd, Lista ListaRev, Lista ListaRand, Tabela TAB)
    {
        Lista AuxOrd = new Lista();
        Lista AuxRev = new Lista();
        Lista AuxRand = new Lista();
        long IniOrd, FimOrd, TotOrd, IniRev, FimRev, TotRev, IniRand, FimRand, TotRand;
        
        System.out.println("***** Inserção Direta *****");
        AuxOrd.kill();
        ListaOrd.copiarLista(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.Ord_InsercaoDireta();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        AuxRev.kill();
        ListaRev.copiarLista(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.Ord_InsercaoDireta();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        AuxRand.kill();
        ListaRand.copiarLista(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.Ord_InsercaoDireta();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Inserção Direta",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Inserção Binária *****");
        AuxOrd.kill();
        ListaOrd.copiarLista(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.Ord_InsercaoBinaria();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        AuxRev.kill();
        ListaRev.copiarLista(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.Ord_InsercaoBinaria();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        AuxRand.kill();
        ListaRand.copiarLista(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.Ord_InsercaoBinaria();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Inserção Binária",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Seleção Direta *****");
        AuxOrd.kill();
        ListaOrd.copiarLista(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.Ord_SelecaoDireta();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        AuxRev.kill();
        ListaRev.copiarLista(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.Ord_SelecaoDireta();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        AuxRand.kill();
        ListaRand.copiarLista(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.Ord_SelecaoDireta();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Seleção Direta",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Bolha *****");
        AuxOrd.kill();
        ListaOrd.copiarLista(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.Ord_Bolha();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        AuxRev.kill();
        ListaRev.copiarLista(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.Ord_Bolha();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        AuxRand.kill();
        ListaRand.copiarLista(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.Ord_Bolha();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Bolha",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Shake *****");
        AuxOrd.kill();
        ListaOrd.copiarLista(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.Ord_Shake();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        AuxRev.kill();
        ListaRev.copiarLista(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.Ord_Shake();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        AuxRand.kill();
        ListaRand.copiarLista(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.Ord_Shake();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Shake",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Shell *****");
        AuxOrd.kill();
        ListaOrd.copiarLista(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.Ord_Shell();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        AuxRev.kill();
        ListaRev.copiarLista(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.Ord_Shell();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        AuxRand.kill();
        ListaRand.copiarLista(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.Ord_Shell();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Shell",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Heap *****");
        AuxOrd.kill();
        ListaOrd.copiarLista(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdHeap();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        AuxRev.kill();
        ListaRev.copiarLista(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdHeap();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        AuxRand.kill();
        ListaRand.copiarLista(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdHeap();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Heap",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Quick Sem Pivo *****");
        AuxOrd.kill();
        ListaOrd.copiarLista(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.Ord_Quick_S_Pivo();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        AuxRev.kill();
        ListaRev.copiarLista(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.Ord_Quick_S_Pivo();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        AuxRand.kill();
        ListaRand.copiarLista(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.Ord_Quick_S_Pivo();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Quick SP",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Quick Com Pivo *****");
        AuxOrd.kill();
        ListaOrd.copiarLista(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.Ord_Quick_C_P();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        AuxRev.kill();
        ListaRev.copiarLista(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.Ord_Quick_C_P();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        AuxRand.kill();
        ListaRand.copiarLista(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.Ord_Quick_C_P();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Quick CP",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Merge 1 *****");
        AuxOrd.kill();
        ListaOrd.copiarLista(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdFusaoDireta_Merge();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        AuxRev.kill();
        ListaRev.copiarLista(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdFusaoDireta_Merge();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        AuxRand.kill();
        ListaRand.copiarLista(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdFusaoDireta_Merge();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Merge 1",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Merge 2 *****");
        AuxOrd.kill();
        ListaOrd.copiarLista(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdMerge2();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        AuxRev.kill();
        ListaRev.copiarLista(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdMerge2();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        AuxRand.kill();
        ListaRand.copiarLista(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdMerge2();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Merge 2",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Counting *****");
        AuxOrd.kill();
        ListaOrd.copiarLista(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdCountingSort();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        AuxRev.kill();
        ListaRev.copiarLista(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdCountingSort();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        AuxRand.kill();
        ListaRand.copiarLista(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdCountingSort();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Counting",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Bucket *****");
        AuxOrd.kill();
        ListaOrd.copiarLista(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdBucketSort(20);
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        AuxRev.kill();
        ListaRev.copiarLista(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdBucketSort(20);
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        AuxRand.kill();
        ListaRand.copiarLista(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdBucketSort(20);
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Bucket",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Radix *****");
        AuxOrd.kill();
        ListaOrd.copiarLista(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdRadixSort();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        AuxRev.kill();
        ListaRev.copiarLista(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdRadixSort();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        AuxRand.kill();
        ListaRand.copiarLista(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdRadixSort();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Radix",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Comb *****");
        AuxOrd.kill();
        ListaOrd.copiarLista(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdCombSort();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        AuxRev.kill();
        ListaRev.copiarLista(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdCombSort();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        AuxRand.kill();
        ListaRand.copiarLista(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdCombSort();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Comb",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Gnome *****");
        AuxOrd.kill();
        ListaOrd.copiarLista(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdGnomeSort();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        AuxRev.kill();
        ListaRev.copiarLista(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdGnomeSort();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        AuxRand.kill();
        ListaRand.copiarLista(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdGnomeSort();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Gnome",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
        
        System.out.println("***** Ordenação Tim *****");
        AuxOrd.kill();
        ListaOrd.copiarLista(AuxOrd);
        IniOrd = System.currentTimeMillis();
        AuxOrd.OrdTimSort();
        FimOrd = System.currentTimeMillis();
        TotOrd = FimOrd - IniOrd;
        
        AuxRev.kill();
        ListaRev.copiarLista(AuxRev);
        IniRev = System.currentTimeMillis();
        AuxRev.OrdTimSort();
        FimRev = System.currentTimeMillis();
        TotRev = FimRev - IniRev;
        
        AuxRand.kill();
        ListaRand.copiarLista(AuxRand);
        IniRand = System.currentTimeMillis();
        AuxRand.OrdTimSort();
        FimRand = System.currentTimeMillis();
        TotRand = FimRand - IniRand;
        
        TAB.inserirLinhaTabela("Tim",
                                AuxOrd.getComp(), 0,
                                AuxOrd.getMov(), 0, TotOrd,
                                AuxRev.getComp(), 0,
                                AuxRev.getMov(), 0, TotRev,
                                AuxRand.getComp(), 0,
                                AuxRand.getMov(), 0, TotRand);
    }
    
    public static void main(String[] args) throws IOException {
//        Arquivos ArqOrd = new Arquivos("ArqOrd.dat");
//        Arquivos ArqRev = new Arquivos("ArqRev.dat");
//        Arquivos ArqRand = new Arquivos("ArqRand.dat");
        Lista ListaOrd = new Lista();
        Lista ListaRev = new Lista();
        Lista ListaRand = new Lista();
        
        //Tabela TAB_Arq = new Tabela("Tabela_Arquivo.txt", "Arquivo Ordenado", "Arquivo Reverso", "Arquivo Randômico");
        Tabela TAB_Lista = new Tabela("Tabela_Lista.txt", "Lista Ordenada", "Lista Reversa", "Lista Randômica");
        
        //gerarArq(ArqOrd, ArqRev, ArqRand);
        gerarListas(ListaOrd, ListaRev, ListaRand);
        //OrdenarArquivos(ArqOrd, ArqRev, ArqRand, TAB_Arq);
        OrdenarListas(ListaOrd, ListaRev, ListaRand, TAB_Lista);
    }
}
