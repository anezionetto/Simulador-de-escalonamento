package Modelo;

import javax.swing.JOptionPane;

public class ListaDupla {

    private No cabeceira;

    public ListaDupla() {
        cabeceira = null;
    }

    public boolean esVacio() {
        return (this.cabeceira == null);
    }

    public int size() {
        int tamanho = 0;
        No aux = cabeceira;
        if (!esVacio()) {
            tamanho++;

            while (aux.seguinte != null) {
                tamanho++;
                aux = aux.seguinte;
            }
        }
        return tamanho;
    }

    public void insertarCabecera(Processo dato) {
        No nuevo = new No(dato);
        if (esVacio()) {
            cabeceira = nuevo;
        }
    }

    public void inserta_inicio(Processo dato) {
        if (!esVacio()) {
            No nuevo = new No(dato);
            if (cabeceira.dado.getTempo() > nuevo.dado.getTempo()) {
                nuevo.seguinte = cabeceira;
                cabeceira.anterior = nuevo;
                cabeceira = nuevo;
            } else {
                cabeceira.seguinte = nuevo;
                nuevo.anterior = cabeceira;
            }
        }

    }

    public void insertarProcesos_Prioridad(Processo dato) {
        No nuevo = new No(dato);
        No aux = cabeceira;
        if (!esVacio()) {
            if (nuevo.dado.getPrioridade() == 0) {
                inserta_inicio(dato);
            } else {
                if (aux.seguinte == null) {
                    inserta_inicio(dato);
                } else {
                    while (nuevo.dado.getPrioridade() > aux.dado.getPrioridade()) {
                        if (aux.seguinte == null) {
                            break;
                        }
                        aux = aux.seguinte;
                    }
                    if (nuevo.dado.getPrioridade() < aux.dado.getPrioridade()) {
                        nuevo.seguinte = aux;
                        aux.anterior.seguinte = nuevo;
                        nuevo.anterior = aux.anterior;
                        aux.anterior = nuevo;
                    } else {
                        aux.seguinte = nuevo;
                        nuevo.anterior = aux;
                    }
                }
            }
        } else {
            cabeceira = nuevo;
        }
    }

    public void Prioridade() {
        No aux = cabeceira;
        No aux2 = cabeceira;
        if (!esVacio()) {
            while (aux2 != null) {
                if (aux.seguinte == null) {
                    break;
                }
                aux = aux.seguinte;
                if (aux2.dado.getPrioridade() == 0) {
                    aux2 = aux2.seguinte;
                    aux2.dado.setBurst(aux2.dado.getBurst() - 1);

                }
                if (aux.dado.getPrioridade() < aux2.dado.getPrioridade()) {
                    aux2.dado.setBurst(aux2.dado.getBurst() - 1);
                    Processo p = new Processo(aux2.dado.getNome(), aux2.dado.getBurst(), aux2.dado.getTempo(),
                            aux2.dado.getPrioridade(), aux2.dado.getEstado());
                    No nuevo = new No(p);
                    if (aux.seguinte == null) {
                        aux.seguinte = nuevo;
                        nuevo.anterior = aux;
                    } else {
                        aux = aux.seguinte;
                        if (nuevo.dado.getPrioridade() < aux.dado.getPrioridade()) {
                            nuevo.seguinte = aux;
                            aux.anterior.seguinte = nuevo;
                            nuevo.anterior = aux.anterior;
                            aux.anterior = nuevo;
                        } else {
                            while (nuevo.dado.getPrioridade() > aux.dado.getPrioridade()) {
                                if (aux.seguinte == null) {
                                    break;
                                }
                                aux = aux.seguinte;
                            }
                            if (nuevo.dado.getPrioridade() < aux.dado.getPrioridade()) {
                                nuevo.seguinte = aux;
                                aux.anterior.seguinte = nuevo;
                                nuevo.anterior = aux.anterior;
                                aux.anterior = nuevo;
                            } else {
                                aux.seguinte = nuevo;
                                nuevo.anterior = aux;
                            }
                        }
                    }
                } else {
                    aux2 = aux2.seguinte;
                    if (aux.seguinte == null) {
                        aux = aux2;
                    }

                }
                if (aux2.seguinte == null) {
                    break;
                }

            }

        }
    }

    public void insertarPrincipio(Processo dado) {
        No nuevo = new No(dado);
        if (esVacio()) {
            insertarCabecera(dado);
        } else {
            insertarInicio(dado);
        }
    }

    public void insertar(int pos, Processo dado) {
        if (!esVacio()) {
            No aux = cabeceira;
            No nuevo = new No(dado);
            if (aux.seguinte == null) {
                inserta_inicio(dado);
            } else if (aux.seguinte.seguinte == null) {
                aux.seguinte = nuevo;
                nuevo.seguinte = aux.seguinte;
                nuevo.anterior = aux;
                aux.seguinte.anterior = nuevo;
            } else {

            }
        }
    }

    public void insertarInicio(Processo dado) {
        No nuevo = new No(dado);
        if (!esVacio()) {
            nuevo.seguinte = cabeceira;
            cabeceira.anterior = nuevo;
            cabeceira = nuevo;
        }
    }

    public void insertarFinal(Processo dado) {
        No aux = cabeceira;
        No nuevo = new No(dado);
        if (!esVacio()) {
            while (aux.seguinte != null) {
                aux = aux.seguinte;
            }
            aux.seguinte = nuevo;
            nuevo.anterior = aux;
        } else {
            insertarCabecera(dado);
        }
    }

    public void insertarPorPosicion(Processo dado, int posicion) {
        if (!esVacio()) {
            if (posicion == 1) {
                insertarInicio(dado);
            } else {
                if (posicion == size()) {
                    insertarFinal(dado);
                } else {
                    if (posicion > 0 && posicion < size()) {
                        No nuevo = new No(dado);
                        No aux = cabeceira;
                        int con = 0;
                        while (con != (posicion - 1)) {
                            aux = aux.seguinte;
                            con++;
                        }
                        No a = aux.seguinte;
                        nuevo.seguinte = aux.seguinte;
                        aux.seguinte = nuevo;
                        nuevo.anterior = aux;
                        a.anterior = nuevo;
                    }
                }

            }

        } else {
            insertarCabecera(dado);
        }
    }

    public void modificar(int pos, Processo dados) {
        No auxiliar = cabeceira;
        int recorrido = 0;
        if (!esVacio()) {
            if (pos == 0) {
                cabeceira.dado = (Processo) dados;
            } else {
                if (pos == size()) {
                    get(pos).dado = (Processo) dados;
                } else {
                    if (pos > 0 & pos < size()) {
                        No nuevo = new No(dados);
                        while (recorrido != (pos - 1)) {
                            auxiliar = auxiliar.seguinte;
                            recorrido++;
                        }
                        nuevo.seguinte = auxiliar.seguinte;
                        auxiliar.seguinte.dado = nuevo.dado;
                    } else {
                        JOptionPane.showMessageDialog(null, "O ELEMENTO É O MAIOR TAMANHO DA LISTA");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "A LISTA ESTÁ VAZIA");
        }

    }

    public No get(int posicao) {
        No aux = cabeceira;
        int contador = 0;
        while (contador != posicao) {
            aux = aux.seguinte;
            contador++;
        }
        return aux;
    }

    public void eliminarLista() {
        if (!esVacio()) {
            cabeceira.seguinte = null;
            cabeceira = null;
        } else {
        }
    }

    public void eliminarInicio() {
        No aux = cabeceira;
        if (cabeceira.seguinte != null) {
            cabeceira = aux.seguinte;
            cabeceira.anterior = null;
            aux.seguinte = null;
        } else {
            cabeceira = null;
        }
    }

    public void eliminarFinal() {
        No auxiliar = cabeceira;
        No eliminar = auxiliar.seguinte;
        if (!esVacio()) {
            if (cabeceira.seguinte != null) {
                while (auxiliar.seguinte.seguinte != null) {
                    auxiliar = auxiliar.seguinte;
                    eliminar = eliminar.seguinte;
                }
            }
            auxiliar.seguinte = null;
            eliminar.anterior = null;
        }
    }

    public void eliminarEntreNodos(int pos) {
        No auxiliar = cabeceira;
        int recorrido = 0;
        if (!esVacio()) {
            if (pos == 0) {
                eliminarInicio();
            } else {
                if (pos == size() - 1) {
                    eliminarFinal();
                } else {
                    if (pos > 0 & pos < size()) {
                        No eliminar = auxiliar.seguinte;
                        while (recorrido != (pos - 1)) {
                            auxiliar = auxiliar.seguinte;
                            eliminar = eliminar.seguinte;
                            recorrido++;
                        }
                        auxiliar.seguinte = eliminar.seguinte;
                        eliminar.seguinte.anterior = auxiliar;
                        eliminar.seguinte = null;
                        eliminar.anterior = null;

                    } else {
                        JOptionPane.showMessageDialog(null, "A POSIÇÃO NÃO EXISTE");

                    }
                }
            }
        }

    }

    public String imprimir() {
        String informacao = "";
        No atual = cabeceira;
        System.out.print("DADOS INGRESSADOS: ");
        while (atual != null) {
            informacao += atual.dado.toString() + "\n";
            atual = atual.seguinte;
        }
        return informacao;
    }

}
