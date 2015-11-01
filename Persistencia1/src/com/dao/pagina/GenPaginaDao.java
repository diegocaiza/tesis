/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.pagina;

import java.util.List;
import modelo1.GenPagina;

/**
 *
 * @author diego
 */
public interface GenPaginaDao {
    public GenPagina listarPaginabyId(Long id);
    public List<GenPagina> listarPagina();
    public boolean addGenPagina(GenPagina genPagina);
    public boolean eliminarGenPagina(GenPagina genPagina);
    
}
