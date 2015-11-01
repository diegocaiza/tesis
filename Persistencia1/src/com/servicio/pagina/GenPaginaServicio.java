/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio.pagina;

import java.util.List;
import modelo1.GenPagina;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author diego
 */
public interface GenPaginaServicio {

    public GenPagina listarPaginabyId(Long id);

    public boolean addGenPagina(GenPagina genPagina);

    public void eliminarGenPagina(List<GenPagina> genPagina);

    public List<GenPagina> listarPagina();

    public void añadirTodosPaginas(List<GenPagina> lista);
}
