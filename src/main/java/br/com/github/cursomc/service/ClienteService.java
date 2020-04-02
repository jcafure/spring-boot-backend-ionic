package br.com.github.cursomc.service;

import br.com.github.cursomc.domain.TipoCliente;
import br.com.github.cursomc.dto.ClienteDTO;
import br.com.github.cursomc.dto.ClienteNewDTO;
import br.com.github.cursomc.exception.DataIntegrityException;
import br.com.github.cursomc.exception.ObjetoNaoEncontradoException;
import br.com.github.cursomc.model.Cidade;
import br.com.github.cursomc.model.Cliente;
import br.com.github.cursomc.model.Endereco;
import br.com.github.cursomc.repository.CidadeRepository;
import br.com.github.cursomc.repository.ClienteRepository;
import br.com.github.cursomc.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService  {

    private final ClienteRepository clienteRepository;
    private final CidadeRepository cidadeRepository;
    private final EnderecoRepository enderecoRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, CidadeRepository cidadeRepository, EnderecoRepository enderecoRepository) {
        this.clienteRepository = clienteRepository;
        this.cidadeRepository = cidadeRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public Cliente findbyId(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() ->
                new ObjetoNaoEncontradoException("Objeto não encontrado! ID: " + id + "tipo " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente = clienteRepository.save(cliente);
        //enderecoRepository.saveAll(cliente.getEnderecos());
        return  cliente;
    }


    public Cliente update(Cliente cliente) {
        Cliente novoCliente = findbyId(cliente.getId());
        updateData(novoCliente, cliente);
        return clienteRepository.save(novoCliente);
    }


    public Cliente buildCategoria(ClienteDTO clienteDTO) {
        return Cliente.builder().id(clienteDTO.getIdCLiente()).nome(clienteDTO.getNomeCliente()).email(clienteDTO.getEmail()).build();
    }

    private void updateData(Cliente novoCliente, Cliente cliente) {
        novoCliente.setNome(cliente.getNome());
        novoCliente.setEmail(cliente.getEmail());
    }

    public void delete(Integer id) {
        findbyId(id);
        try{
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos.");
        }
    }

    public List<ClienteDTO> findAll() {
        List<Cliente> categorias = clienteRepository.findAll();
        return categorias.stream().map(categoria -> new ClienteDTO(categoria)).collect(Collectors.toList());
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.ASC, "nome");
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente buildClienteFromDTO(ClienteNewDTO clienteNewDTO) {
        Cliente cliente = new Cliente(clienteNewDTO);
        //Cidade cidade = new Cidade(clienteNewDTO);
        Optional<Cidade> cidade = cidadeRepository.findById(clienteNewDTO.getCidadeId());
        Endereco endereco = new Endereco(clienteNewDTO, cliente, cidade.get());
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(clienteNewDTO.getTelefone1());
        if (clienteNewDTO.getTelefone2()!=null) {
            cliente.getTelefones().add(clienteNewDTO.getTelefone2());
        }
        if (clienteNewDTO.getTelefone3()!=null) {
            cliente.getTelefones().add(clienteNewDTO.getTelefone3());
        }
        return cliente;
    }


}
