package com.arcanjo.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.arcanjo.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;

	public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento,
			Date dataPagamento) {
		super(id, estadoPagamento.getCod(), pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public PagamentoComBoleto() {
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Override
	public String toString() {
		return "PagamentoComBoleto [dataVencimento=" + dataVencimento + ", dataPagamento=" + dataPagamento
				+ ", getId()=" + getId() + ", getEstadoPagamento()=" + getEstadoPagamento() + ", getPedido()="
				+ getPedido() + "]";
	}

}
