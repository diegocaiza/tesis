/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.pagina;

import java.io.Serializable;
import java.util.List;
import modelo1.GenPagina;
import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

public class GenPaginaDaoImpl implements GenPaginaDao, Serializable {

    private static final long serialVersionUID = 1L;
//    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public GenPagina listarPaginabyId(Long id) {
        GenPagina result = new GenPagina();
        try {
            result = (GenPagina) getSessionFactory().getCurrentSession().get(GenPagina.class, id);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addGenPagina(GenPagina genPagina) {
        try {
            getSessionFactory().getCurrentSession().saveOrUpdate(genPagina);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<GenPagina> listarPagina() {
        List list = getSessionFactory()
                .getCurrentSession().createQuery("from  GenPagina "
                + " where genPaginaEstado=1 order by genPaginaId desc").list();
        return list;
    }

    @Override
    public boolean eliminarGenPagina(GenPagina genPagina) {
        try {
            getSessionFactory().getCurrentSession().saveOrUpdate(genPagina);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
