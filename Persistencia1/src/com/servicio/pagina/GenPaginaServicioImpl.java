/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio.pagina;

import com.dao.pagina.GenPaginaDao;
import java.io.Serializable;
import java.util.List;
import modelo1.GenPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author diego
 */
@Transactional(readOnly = true)
public class GenPaginaServicioImpl implements GenPaginaServicio, Serializable {

    private static final long serialVersionUID = 1L;
//    @Autowired
    GenPaginaDao genPaginaDao;

    @Transactional(readOnly = false)
    @Override
    public GenPagina listarPaginabyId(Long id) {
        return getGenPaginaDao().listarPaginabyId(id);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean addGenPagina(GenPagina genPagina) {
        return getGenPaginaDao().addGenPagina(genPagina);
    }

    public GenPaginaDao getGenPaginaDao() {
        return genPaginaDao;
    }

    public void setGenPaginaDao(GenPaginaDao genPaginaDao) {
        this.genPaginaDao = genPaginaDao;
    }

    @Transactional(readOnly = false)
    @Override
    public void eliminarGenPagina(List<GenPagina> lista) {
          for (GenPagina genPagina : lista) {
              genPagina.setGenPaginaEstado(2);
            getGenPaginaDao().eliminarGenPagina(genPagina);
        }
        

    }

    @Transactional(readOnly = false)
    @Override
    public List<GenPagina> listarPagina() {
        return getGenPaginaDao().listarPagina();
    }

    @Transactional(readOnly = false)
    @Override
    public void añadirTodosPaginas(List<GenPagina> lista) {

        for (GenPagina genPagina : lista) {
            getGenPaginaDao().addGenPagina(genPagina);
        }
    }
}
