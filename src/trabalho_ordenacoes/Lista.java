package trabalho_ordenacoes;

public class Lista {
    private No inicio;
    private No fim;
    private final int TF = 4096;
    private int mov=0, comp=0;

    public int getMov() {
        return mov;
    }

    public void setMov(int mov) {
        this.mov = mov;
    }

    public int getComp() {
        return comp;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }
    
    public Lista(){
        this.inicio = null;
        this.fim = null;
    }

    public No getInicio() {
        return inicio;
    }

    public void setInicio(No inicio) {
        this.inicio = inicio;
    }
    
    public void gerarListaOrdenada(){
        for (int i = 0; i < TF; i++)
            this.inserirNoFinal(i);
    }
    
    public void gerarListaReversa(){
        for (int i = TF; i > 0; i--)
            this.inserirNoFinal(i);
    }
    
    public void gerarListaRandomica(){
        for (int i = 0; i < TF; i++)
            this.inserirNoFinal((int)(Math.random()*1025));
    }
    
    public void exibeLista(){
        No no = this.inicio;
        while(no != null){
            System.out.print(no.getInfo() + " | ");
            no = no.getProx();
        }
    }
    
    public void exibeListaReversa()
    {
        No no = this.fim;
        while(no != null)
        {
            System.out.print(no.getInfo() + " | ");
            no = no.getAnt();
        }
    }
    
    public No posiciona(int pos){
        No pont = inicio;
        int i=0;
        for(; i<pos; pont=pont.getProx(),i++);
        return pont;
    }
    
    public int TL(){
        No aux = inicio;
        int i=0;
        for(;aux!=null; i++, aux=aux.getProx());
        return i;
    }
    
    public void copiarLista(Lista L)
    {
        No aux = inicio;
        while(aux != null)
        {
            L.inserirNoFinal(aux.getInfo());
            aux = aux.getProx();
        }
    }
    
    public boolean vazio(){
        return this.inicio == null;
    }
    
    public void inserirNoFinal(int num){
        if(this.fim == null)
            this.inicio = this.fim = new No(null, num, null);
        else{
            No caixa = new No(this.fim, num, null);
            this.fim.setProx(caixa);
            this.fim = caixa;
        }
    }
    
    public void inserirNoInicio(int num){
        if(this.fim == null)
            this.inicio = this.fim = new No(null, num, null);
        else{
            No caixa = new No(null, num, this.inicio);
            this.inicio.setProx(caixa);
            this.inicio = caixa;
        }
    }
    
    public int popCodigo(){
        No aux = inicio;
        inicio = inicio.getProx();
        if(inicio == null)
            fim = null;
        return aux.getInfo();
    }
    
    // FUNCOES DE ORDENACAO
    
    public void Ord_InsercaoDireta()
    {
        Ord_InsercaoDireta(inicio, fim);
    }
    
    public void Ord_InsercaoDireta(No inicio, No fim){
        int aux;
        No no = inicio.getProx(), noo;
        while(no != fim.getProx())
        {
            noo = no;
            aux = noo.getInfo();
            comp++;
            while(noo != inicio && aux < noo.getAnt().getInfo())
            {
                noo.setInfo(noo.getAnt().getInfo());
                noo = noo.getAnt();
                mov++;
            }
            noo.setInfo(aux);
            no = no.getProx();
            mov++;
        }
    }
    
    private int busca_binaria_inserindo(int chave, int TL){
        int inicio = 0, fim = TL- 1, meio = fim / 2;
        No no;
        no = posiciona(inicio);
        while (fim != inicio && no.getInfo() != chave)
        {
            no = posiciona(meio);
            comp++;
            if (chave > no.getInfo())
                inicio = meio + 1;
            else
                fim = meio;
            meio = (inicio + fim) / 2;
            no = posiciona(inicio);
        }
        no = posiciona(meio);
        comp++;
        if (chave > no.getInfo())
            return meio + 1;
        return meio;
    }
    
    public void Ord_InsercaoBinaria(){
        int i, aux, pos, TL = TL();
        No no;
        for (i = 1; i < TL; i++)
        {
            no = posiciona(i);
            aux = no.getInfo();
            pos = busca_binaria_inserindo(aux, i);
            for (int j = i; j > pos; j--)
            {
                comp++;
                no.setInfo(no.getAnt().getInfo());
                no = no.getAnt();
                mov++;
            }
            no = posiciona(pos);
            no.setInfo(aux);
        }
    }
    
    public void Ord_SelecaoDireta(){
        int menor, posmenor, TL = TL();
        No no, aux;
        for (int i = 0; i < TL - 1; i++)
        {
            no = posiciona(i);
            menor = no.getInfo();
            posmenor = i;
            for (int j = i + 1; j < TL; j++)
            {
                no = posiciona(j);
                comp++;
                if (no.getInfo() < menor)
                {
                    menor = no.getInfo();
                    posmenor = j;
                }
            }
            no = posiciona(posmenor);
            aux = posiciona(i);
            no.setInfo(aux.getInfo());
            aux.setInfo(menor);
            mov+=2;
        }
    }
    
    public void Ord_Bolha() {
        int TL2 = TL(), aux;
        No no, noo;
        while (TL2 > 1)
        {
            for (int i = 0; i < TL2 - 1; i++)
            {
                no = posiciona(i);
                noo = no.getProx();
                comp++;
                if (no.getInfo() > noo.getInfo())
                {
                    aux = no.getInfo();
                    no.setInfo(noo.getInfo());
                    noo.setInfo(aux);
                    mov+=2;
                }
            }
            TL2--;
        }
    }
    
    public void Ord_Shake(){
        int aux, inicio = 0, fim = TL() - 1;
        No no , noo;
        while (inicio < fim)
        {
            comp++;
            //Ir do inicio para o final
            for (int i = 0; i < fim; i++)
            {
                no = posiciona(i);
                noo = no.getProx();
                comp++;
                if (no.getInfo() > noo.getInfo())
                {
                    aux = no.getInfo();
                    no.setInfo(noo.getInfo());
                    noo.setInfo(aux);
                    mov++;
                }
            }
            fim--;
            //Ir do final para o inicio
            for (int i = fim; i > inicio; i--)
            {
                no = posiciona(i);
                noo = no.getAnt();
                comp++;
                if (no.getInfo() < noo.getInfo())
                {
                    aux = no.getInfo();
                    no.setInfo(noo.getInfo());
                    noo.setInfo(aux);
                    mov++;
                }
            }
            inicio++;
        }
    }
    
    public void Ord_Shell(){
        int aux, TL = TL();
        No noj, noj_dist, nok, nok_dist;
        for (int dist = 4; dist > 0; dist = dist / 2)
        {
            for (int i = 0; i < dist; i++)
            { // grupos
                int j = i;
                while (j + dist < TL)
                {
                    noj = posiciona(j);
                    noj_dist = posiciona(j+dist);
                    comp++;
                    if (noj.getInfo() > noj_dist.getInfo())
                    {
                        //permutacao
                        aux = noj.getInfo();
                        noj.setInfo(noj_dist.getInfo());
                        noj_dist.setInfo(aux);
                        
                        int k = j;
                        nok = posiciona(k);
                        nok_dist = posiciona(k-dist);
                        comp++;
                        while (k - dist >= i && nok_dist.getInfo() > nok.getInfo())
                        {
                            //permutacao
                            aux = nok.getInfo();
                            nok.setInfo(nok_dist.getInfo());
                            nok_dist.setInfo(aux);
                            comp+=2;
                            mov+=2;
                            k -= dist;
                        }
                    }
                    j += dist;
                }
            }
        }
    }
    
    public void OrdHeap(){
        int pai, TL = TL();
        int aux, FE, FD;
        No noFE, noFD, noPAI, noMaiorF, fim = this.fim;
        for (int TLAux = TL; TLAux > 1; TLAux--)
        {
            pai = TLAux/2-1;
            noPAI = posiciona(pai);
            while(pai >= 0)
            {
                FE = pai * 2 + 1;
                FD = FE+1;
                noFE = posiciona(FE);
                noFD = noFE.getProx();
                comp++;
                if (FD < TLAux && noFD.getInfo() > noFE.getInfo())
                    noMaiorF = noFD;
                else
                    noMaiorF = noFE;
                comp++;
                if (noMaiorF.getInfo() > noPAI.getInfo())
                {
                    aux = noMaiorF.getInfo();
                    noMaiorF.setInfo(noPAI.getInfo());
                    noPAI.setInfo(aux);
                    mov++;
                }
                noPAI = noPAI.getAnt();
                pai--;
            }
            aux = inicio.getInfo();
            inicio.setInfo(fim.getInfo());
            fim.setInfo(aux);
            fim = fim.getAnt();
            mov++;
        }
    }
    
    public void Ord_Quick_S_Pivo(){ // ALGORITMO RECURSIVO
        QuickSP(inicio, fim);
    }
    
    private void QuickSP(No inicio, No fim){
        int aux;
        No i = inicio, j = fim;
        boolean flag = true;
        while (i != j)
        {
            if (flag)
                for (; i != j && i.getInfo() <= j.getInfo(); i=i.getProx(), comp++);
            else
                for (; i != j && j.getInfo() >= i.getInfo(); j=j.getAnt(), comp++);
            flag = !flag;
            
            aux = j.getInfo();
            i.setInfo(j.getInfo());
            j.setInfo(aux);
            mov++;
        }
        if (inicio != j)
            QuickSP(inicio, j.getAnt());
        if (j != fim)
            QuickSP(i.getProx(), fim);
    }
    
    public void Ord_Quick_C_P(){
        QuickCP(inicio,fim);
    }
    
    private int pos(No no){
        int i=0;
        No aux = inicio;
        while(aux != no)
        {
            aux = aux.getProx();
            i++;
        }
        return i;
    }
    
    private void QuickCP(No inicio, No fim){
        // ↓↓↓ PRECISO SABER QUAL A POSICAO DO INICIO E FIM PARA POSICIONAR O PIVO 
        int aux, posini = pos(inicio), posfim = pos(fim);
        No pivo = posiciona((posini+posfim)/2);
        No i = inicio, j = fim;
        while(pos(i) < pos(j))
        {
            comp++;
            while(i.getInfo() < pivo.getInfo())
            {
                i = i.getProx();
                comp++;
            }
            while(j.getInfo() > pivo.getInfo())
            {
                j = j.getAnt();
                comp++;
            }
            if(pos(i) <= pos(j))
            {
                aux = i.getInfo();
                i.setInfo(j.getInfo());
                j.setInfo(aux);
                mov++;
                if(i.getProx()!=null)
                    i=i.getProx();
                if(j.getAnt()!=null)
                    j=j.getAnt();
            }
        }
        if (pos(inicio) < pos(j))
            QuickCP(inicio, j);
        if (pos(i) < pos(fim))
            QuickCP(i, fim);
    }
    
    public void kill()
    {
        this.inicio = this.fim = null;
    }
    
    public void OrdFusaoDireta_Merge(){
        int seq = 1, tam_seq, i, j, k, meio = TL()/2, TL = TL();
        Lista L1 = new Lista();
        Lista L2 = new Lista();
        No no1, no2;
        // gravar os numeros nos vetores auxiliares;
        while(seq < TL)
        {
            comp++;
            L1.kill();
            L2.kill();
            for(i=0; i<meio ; i++)
            {
                L1.inserirNoFinal(this.posiciona(i).getInfo());
                L2.inserirNoFinal(this.posiciona(i+meio).getInfo());
                comp++;
                mov+=2;
            }
            i=j=k=0;
            tam_seq = seq;
            while(k<TL)
            {
                comp++;
                // controlar a sequencia
                no1 = L1.posiciona(i);
                no2 = L2.posiciona(j);
                while(i<tam_seq && j<tam_seq)
                {
                    comp++;
                    if(no1.getInfo() < no2.getInfo())
                    {
                        this.posiciona(k++).setInfo(no1.getInfo());
                        no1 = no1.getProx();
                        i++;
                    }
                    else
                    {
                        this.posiciona(k++).setInfo(no2.getInfo());
                        no2 = no2.getProx();
                        j++;
                    }
                }
                while(i<tam_seq)
                {
                    comp++;
                    this.posiciona(k++).setInfo(no1.getInfo());
                    no1 = no1.getProx();
                    mov++;
                    i++;
                }
                while(j<tam_seq)
                {
                    comp++;
                    this.posiciona(k++).setInfo(no2.getInfo());
                    no2 = no2.getProx();
                    mov++;
                    j++;
                }
                tam_seq += seq;
            }
            seq *= 2;
        }
    }
    
    public void OrdMerge2(){
        Lista LAux = new Lista();
        OrdMerge2(0, this.TL()-1, LAux);
    }
    
    private void fusao(int inicio1, int fim1, int inicio2, int fim2, Lista LAux){
        int i = inicio1, j = inicio2;
        while(i<=fim1 && j<=fim2)
        {
            comp++;
            if(this.posiciona(i).getInfo() < this.posiciona(j).getInfo())
                LAux.inserirNoFinal(this.posiciona(i++).getInfo());
            else
                LAux.inserirNoFinal(this.posiciona(j++).getInfo());
        }
        while(i<=fim1)
        {
            LAux.inserirNoFinal(this.posiciona(i++).getInfo());
            comp++;
            mov++;
        }
            
        while(j<=fim2)
        {
            LAux.inserirNoFinal(this.posiciona(j++).getInfo());
            comp++;
            mov++;
        }   
        int TL = LAux.TL();
        for(i=0; i<TL; i++)
            this.posiciona(inicio1 + i).setInfo(LAux.popCodigo());
    }
    
    private void OrdMerge2(int esq, int dir, Lista LAux){
        int meio;
        comp++;
        if(esq<dir)
        {
            meio = (esq+dir)/2;
            OrdMerge2(esq, meio, LAux);
            OrdMerge2(meio+1, dir, LAux);
            fusao(esq,meio,meio+1,dir,LAux);
        }
    }
    
    private int Maior()
    {
        No aux = inicio;
        int maior = aux.getInfo();
        while(aux != null)
        {
            if(aux.getInfo() > maior)
                maior = aux.getInfo();
            aux = aux.getProx();
        }
        return maior;
    }
    
    public void OrdCountingSort()
    {
        int TL = TL(), cod;
        int maior = Maior();
        int vet[] = new int[maior+1];
        Lista LAux = new Lista();
        for(int i=0; i < TL; i++)
        {
            LAux.inserirNoFinal(this.posiciona(i).getInfo());
            mov++;
        }
            
        for(int i=0; i < TL; i++)
        {
            vet[LAux.posiciona(i).getInfo()]++;
            mov++;
        }
        for(int i=0; i < maior; i++)
            vet[i+1] = vet[i] + vet[i+1];
        for(int i=0; i < TL; i++)
        {
            cod = LAux.posiciona(i).getInfo();
            this.posiciona(vet[cod]-1).setInfo(cod);
            mov++;
        }
    }
    
    private int Menor()
    {
        No aux = inicio;
        int menor = aux.getInfo();
        while(aux != null)
        {
            if(aux.getInfo() < menor)
                menor = aux.getInfo();
            aux = aux.getProx();
        }
        return menor;
    }
    
    public void OrdBucketSort(int qtde)
    {
        int TL = TL(), cod;
        int maior = Maior(), menor = Menor();
        int dist = ((maior - menor)/qtde)+1;
        Lista LPonteiros[] = new Lista[qtde];
        for(int i=0; i < qtde; i++)
            LPonteiros[i] = new Lista();
        for(int i=0; i < TL; i++)
        {
            cod = this.posiciona(i).getInfo();
            LPonteiros[(cod-menor)/dist].inserirNoFinal(cod);
            mov++;
        }
        for(int i=0, j=0; i < qtde; i++)
        {
            if(LPonteiros[i].inicio != null)
            {
                LPonteiros[i].Ord_Quick_C_P();
                while(LPonteiros[i].inicio != null)
                {
                    this.posiciona(j++).setInfo(LPonteiros[i].popCodigo());
                    mov++;
                }
            }
        }
    }
    
    public void OrdRadixSort()
    {
        int maior = Maior();
        int exp = 1;
        No no;
        Lista LPonteiros[] = new Lista[10];
        for(int i=0; i < 10; i++)
            LPonteiros[i] = new Lista();
        while(maior/exp > 0)
        {
            no = inicio;
            while(no != null)
            {
                LPonteiros[no.getInfo() / exp % 10].inserirNoFinal(no.getInfo());
                no = no.getProx();
                mov++;
            }
            
            no = inicio;
            for(int i=0; i < 10; i++)
            {
                while(!LPonteiros[i].vazio())
                {
                    no.setInfo(LPonteiros[i].popCodigo());
                    no = no.getProx();
                    mov++;
                }
            }
            exp *= 10;
        }
    }
    
    public void OrdCombSort()
    {
        No no, noo;
        int aux, dist = TL(), TL = TL();
        
        dist /= 1.3;
        comp++;
        while(dist > 0)
        {
            comp+=2;
            for(int i=0; i + dist < TL; i++)
            {
                no = posiciona(i);
                noo = posiciona(i+dist);
                comp++;
                if(no.getInfo() > noo.getInfo())
                {
                    aux = no.getInfo();
                    no.setInfo(noo.getInfo());
                    noo.setInfo(aux);
                    mov+=2;
                }
            }
            dist /= 1.3;
        }
    }
    
    public void OrdGnomeSort()
    {
        int TL = TL(), j, aux;
        No no, noo;
        boolean flag;
        for(int i=1; i < TL; i++)
        {
            no = posiciona(i);
            noo = posiciona(i-1);
            comp++;
            if(no.getInfo() < noo.getInfo())
            {
                aux = no.getInfo();
                no.setInfo(noo.getInfo());
                noo.setInfo(aux);
                mov++;
                flag = true;
                j = i-1;
                while(j > 0 && flag)
                {
                    no = posiciona(j);
                    noo = posiciona(j-1);
                    comp++;
                    if(no.getInfo() < noo.getInfo())
                    {
                        aux = no.getInfo();
                        no.setInfo(noo.getInfo());
                        noo.setInfo(aux);
                        mov+=2;
                    }
                    else
                        flag = false;
                    j--;
                }
            }
        }
    }
    
    public void OrdTimSort()
    {
        Lista LAux = new Lista();
        No noI, noF;
        int dist = 32, TL = TL();
        for(int i=0; i < TL; i+=dist)
        {
            comp++;
            noI = inicio;
            noF = posiciona(i + dist-1);
            if(noF != null)
            {
                Ord_InsercaoDireta(noI, noF);
                noI = noF.getProx();
            }
            else
                Ord_InsercaoDireta(noI, fim);
        }
        comp++;
        while(dist < TL)
        {
            comp++;
            noI = noF = inicio;
            for(int i=0; i < TL; i+= dist*2)
            {
                comp++;
                noF = posiciona(i+dist-1);
                if(noF != null)
                {
                    fusao(i, i+dist-1, i+dist, Math.min(i+dist*2-1, TL-1), LAux);
                    noI = noF.getProx();
                }
                else
                    fusao(i, i+dist-1, i+dist, pos(fim), LAux);
            }
            dist *= 2;
        }
    }
}
