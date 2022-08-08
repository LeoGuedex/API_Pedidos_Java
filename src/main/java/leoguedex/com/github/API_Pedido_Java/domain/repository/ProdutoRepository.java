package leoguedex.com.github.API_Pedido_Java.domain.repository;

import leoguedex.com.github.API_Pedido_Java.domain.entity.Categoria;
import leoguedex.com.github.API_Pedido_Java.domain.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

   @Transactional(readOnly = true)
   @Query("select distinct obj " +
           "from Produto obj" +
           " inner join obj.categorias cat " +
           "where obj.nome like %:nome% and cat in :categorias")
   Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);

}
