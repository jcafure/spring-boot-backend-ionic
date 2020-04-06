package br.com.github.cursomc.repository;

import br.com.github.cursomc.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<Pedido, Integer> {
}
