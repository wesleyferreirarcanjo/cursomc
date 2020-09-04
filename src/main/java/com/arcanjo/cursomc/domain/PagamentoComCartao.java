package com.arcanjo.cursomc.domain;

import javax.persistence.Entity;

import com.arcanjo.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer numeroDeParcelas;

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}
	
	

	public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estadoPagamento.getCod(), pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	public PagamentoComCartao() {}


	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

	@Override
	public String toString() {
		return "PagamentoComCartao [numeroDeParcelas=" + numeroDeParcelas + ", getId()=" + getId()
				+ ", getEstadoPagamento()=" + getEstadoPagamento() + ", getPedido()=" + getPedido() + "]";
	}
	
	
	
}
