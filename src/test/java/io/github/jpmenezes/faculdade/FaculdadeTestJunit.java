package io.github.jpmenezes.faculdade;

import static org.junit.jupiter.api.Assertions.*;

import io.github.jpmenezes.faculdade.modules.celulares.entities.Celular;
import io.github.jpmenezes.faculdade.modules.celulares.repositories.CelularEmMemoriaRepository;
import io.github.jpmenezes.faculdade.modules.celulares.services.CelularService;
import io.github.jpmenezes.faculdade.modules.cursos.entities.Curso;
import io.github.jpmenezes.faculdade.modules.cursos.repositories.CursoEmMemoriaRepository;
import io.github.jpmenezes.faculdade.modules.cursos.services.CursoService;
import io.github.jpmenezes.faculdade.modules.estudantes.entities.Estudante;
import io.github.jpmenezes.faculdade.modules.estudantes.repositories.EstudanteEmMemoriaRepository;
import io.github.jpmenezes.faculdade.modules.estudantes.services.EstudanteService;
import io.github.jpmenezes.faculdade.modules.estudantesMatriculados.entities.EstudantesMatriculados;
import io.github.jpmenezes.faculdade.modules.estudantesMatriculados.repositories.EstudantesMatriculadosEmMemoriaRepository;
import io.github.jpmenezes.faculdade.modules.estudantesMatriculados.services.EstudantesMatriculadosService;
import io.github.jpmenezes.faculdade.modules.turmaCurso.entities.TurmaCurso;
import io.github.jpmenezes.faculdade.modules.turmaCurso.repositories.TurmaCursoEmMemoriaRepository;
import io.github.jpmenezes.faculdade.modules.turmaCurso.services.TurmaCursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FaculdadeTestJunit {

    private EstudanteService estudanteService;
    private CelularService celularService;
    private CursoService cursoService;
    private TurmaCursoService turmaCursoService;
    private EstudantesMatriculadosService estudantesMatriculadosService;

    @BeforeEach
    public void setup() {
        // Configurar serviços com repositórios em memória simulados
        EstudanteEmMemoriaRepository estudanteRepository = new EstudanteEmMemoriaRepository();
        estudanteService = new EstudanteService(estudanteRepository);

        CelularEmMemoriaRepository celularRepository = new CelularEmMemoriaRepository();
        celularService = new CelularService(celularRepository);

        CursoEmMemoriaRepository cursoRepository = new CursoEmMemoriaRepository();
        cursoService = new CursoService(cursoRepository);

        TurmaCursoEmMemoriaRepository turmaCursoRepository = new TurmaCursoEmMemoriaRepository();
        turmaCursoService = new TurmaCursoService(turmaCursoRepository);

        EstudantesMatriculadosEmMemoriaRepository estudantesMatriculadosEmMemoriaRepository = new EstudantesMatriculadosEmMemoriaRepository();
        estudantesMatriculadosService = new EstudantesMatriculadosService(estudantesMatriculadosEmMemoriaRepository);
    }

    /**
     * Classe Celular
     */

    @Test
    public void deveriaInserirCelularComSucesso() {
        // Criar um novo estudante
        Estudante estudante = new Estudante();
        estudante.setNome("João");
        estudante.setMatricula("12345");

        // Criar um novo celular associado ao estudante
        Celular celular = new Celular();
        celular.setNumero("987654321");
        celular.setEstudante(estudante);

        // Chamar o método execute do serviço
        Celular celularCadastrado = celularService.execute(celular);

        // Verificar se o celular foi cadastrado corretamente
        assertNotNull(celularCadastrado.getId());
    }

    @Test
    public void deveriaLancarExcecaoAoInserirCelularSemEstudante() {
        // Criar um novo celular sem associá-lo a um estudante
        Celular celular = new Celular();
        celular.setNumero("987654321");

        // Chamar o método execute do serviço e esperar uma exceção
        assertThrows(IllegalArgumentException.class, () -> celularService.execute(celular));
    }

    /**
     * Classe Estudante
     */

    @Test
    void deveriaCadastrarEstudanteComSucesso() {
        // Criar um novo estudante
        Estudante estudante = new Estudante();
        estudante.setNome("João");
        estudante.setMatricula("12345");
        estudante.setDataDeNascimento(LocalDate.of(2000, 1, 1));
        estudante.setEndereco("Rua ABC, 123");
        estudante.setEmailInstitucional("joao@example.com");

        //Criando um celular
        Celular celular = new Celular();
        celular.setEstudante(estudante);
        celular.setNumero("838488232");

        //Adicionando a uma lista
        List<Celular> celulares = new ArrayList<>();
        celulares.add(celular);

        //Adicionando a lista ao Estudante
        estudante.setCelulares(celulares);

        // Chamar o método cadastrarEstudante do serviço
        Estudante estudanteCadastrado = estudanteService.execute(estudante);

        // Verificar se o estudante foi cadastrado corretamente
        assertNotNull(estudanteCadastrado.getId());
        assertEquals("João", estudanteCadastrado.getNome());
        assertEquals("12345", estudanteCadastrado.getMatricula());
        assertEquals(LocalDate.of(2000, 1, 1), estudanteCadastrado.getDataDeNascimento());
        assertEquals("Rua ABC, 123", estudanteCadastrado.getEndereco());
        assertEquals("joao@example.com", estudanteCadastrado.getEmailInstitucional());

    }

    @Test
    void deveriaLancarExcecaoAoCadastrarEstudanteSemCamposObrigatorios() {
        Estudante estudanteSemNome = new Estudante();
        estudanteSemNome.setMatricula("123");

        assertThrows(IllegalArgumentException.class, () -> estudanteService.execute(estudanteSemNome));

        Estudante estudanteSemMatricula = new Estudante();
        estudanteSemMatricula.setNome("João");

        assertThrows(IllegalArgumentException.class, () -> estudanteService.execute(estudanteSemMatricula));
    }

    @Test
    void deveriaLancarExcecaoAoCadastrarEstudanteComMenosDe15Anos() {
        Estudante estudanteMenorDeIdade = new Estudante();
        estudanteMenorDeIdade.setNome("João");
        estudanteMenorDeIdade.setMatricula("123");
        estudanteMenorDeIdade.setDataDeNascimento(LocalDate.now().minusYears(14));

        assertThrows(IllegalArgumentException.class, () -> estudanteService.execute(estudanteMenorDeIdade));
    }

    /**
     * Classe Curso
     */

    @Test
    void deveriaInserirCursoComSucesso() {
        // Criar um novo curso
        Curso curso = new Curso();
        curso.setNome("Medicina");
        curso.setCargaHoraria("4000");
        curso.setDescricao("Curso de Engenharia");

        // Chamar o método execute do serviço
        Curso cursoCriado = cursoService.execute(curso);

        // Verificar se o ID foi atribuído
        assertNotNull(cursoCriado.getId());
        // Adicionar mais verificações conforme necessário
    }

    @Test
    void deveriaLancarExcecaoAoCriarCursoSemCamposObrigatorios() {
        // Criar um novo curso sem campos obrigatórios
        Curso cursoSemCamposObrigatorios = new Curso();

        // Chamar o método execute do serviço e verificar exceção
        assertThrows(IllegalArgumentException.class, () -> cursoService.execute(cursoSemCamposObrigatorios));
    }

    @Test
    void deveriaLancarExcecaoAoCriarCursoComNomeJaExistente() {
        // Criar um novo curso
        Curso curso1 = new Curso();
        curso1.setNome("Engenharia");
        curso1.setCargaHoraria("4000");
        curso1.setDescricao("Curso de Engenharia");

        // Criar outro curso com o mesmo nome
        Curso curso2 = new Curso();
        curso2.setNome("Engenharia");
        curso2.setCargaHoraria("3000");
        curso2.setDescricao("Curso de Engenharia Civil");

        // Chamar o método execute do serviço para criar o primeiro curso
        cursoService.execute(curso1);

        // Chamar o método execute do serviço para criar o segundo curso e verificar exceção
        assertThrows(RuntimeException.class, () -> cursoService.execute(curso2));
    }

    /**
     * Classe TurmaCurso
     */

    @Test
    void deveriaInserirTurmaCursoComSucesso() {
        // Criar uma instância de TurmaCurso completa para teste
        TurmaCurso turmaCurso = new TurmaCurso();
        turmaCurso.setLocal("Local Teste");
        turmaCurso.setVagas(10);
        turmaCurso.setInicioAulas(LocalDate.of(2023, 1, 1));
        turmaCurso.setFimAulas(LocalDate.of(2023, 12, 31));
        turmaCurso.setInicioMatriculas(LocalDate.of(2022, 11, 1));
        turmaCurso.setFimMatriculas(LocalDate.of(2022, 12, 31));

        // Chamar o método execute do serviço
        TurmaCurso turmaCadastrada = turmaCursoService.execute(turmaCurso);

        // Verificar se a turma foi cadastrada corretamente
        assertNotNull(turmaCadastrada.getId());
        assertEquals("Local Teste", turmaCadastrada.getLocal());
        assertEquals(10, turmaCadastrada.getVagas());
        assertEquals(LocalDate.of(2023, 1, 1), turmaCadastrada.getInicioAulas());
        assertEquals(LocalDate.of(2023, 12, 31), turmaCadastrada.getFimAulas());
        assertEquals(LocalDate.of(2022, 11, 1), turmaCadastrada.getInicioMatriculas());
        assertEquals(LocalDate.of(2022, 12, 31), turmaCadastrada.getFimMatriculas());
    }

    @Test
    void deveriaListarTurmasComEstudantesMatriculados() {
        // Criar uma instância de TurmaCurso completa para teste
        TurmaCurso turmaComEstudantes = new TurmaCurso();
        turmaComEstudantes.setLocal("Local Teste");
        turmaComEstudantes.setVagas(10);
        turmaComEstudantes.setInicioAulas(LocalDate.of(2023, 1, 1));
        turmaComEstudantes.setFimAulas(LocalDate.of(2023, 12, 31));
        turmaComEstudantes.setInicioMatriculas(LocalDate.of(2022, 11, 1));
        turmaComEstudantes.setFimMatriculas(LocalDate.of(2022, 12, 31));

        // Criar um estudante
        Estudante estudante = new Estudante();
        estudante.setNome("João");
        estudante.setMatricula("12345");

        // Criar uma instância de EstudantesMatriculados associada ao estudante
        EstudantesMatriculados estudantesMatriculados = new EstudantesMatriculados();
        estudantesMatriculados.setEstudante(estudante);
        estudantesMatriculados.setDataMatricula(LocalDate.of(2022, 12, 15));

        // Adicionar EstudantesMatriculados à turmaComEstudantes
        List<EstudantesMatriculados> estudantesMatriculadosList = new ArrayList<>();
        estudantesMatriculadosList.add(estudantesMatriculados);
        turmaComEstudantes.setEstudantesMatriculados(estudantesMatriculadosList);

        // Chamar o método execute do serviço para cadastrar a turmaComEstudantes
        TurmaCurso turmaCadastradaComEstudantes = turmaCursoService.execute(turmaComEstudantes);

        // Criar uma segunda instância de TurmaCurso completa sem estudantes matriculados
        TurmaCurso turmaSemEstudantes = new TurmaCurso();
        turmaSemEstudantes.setLocal("Outro Local");
        turmaSemEstudantes.setVagas(5);
        turmaSemEstudantes.setInicioAulas(LocalDate.of(2023, 1, 1));
        turmaSemEstudantes.setFimAulas(LocalDate.of(2023, 12, 31));
        turmaSemEstudantes.setInicioMatriculas(LocalDate.of(2022, 11, 1));
        turmaSemEstudantes.setFimMatriculas(LocalDate.of(2022, 12, 31));

        // Chamar o método execute do serviço para cadastrar a turmaSemEstudantes
        TurmaCurso turmaCadastradaSemEstudantes = turmaCursoService.execute(turmaSemEstudantes);

        // Chamar o método listarTurmasComEstudantesMatriculados e verificar se inclui a turmaComEstudantes
        List<TurmaCurso> turmasComEstudantes = turmaCursoService.listarTurmasComEstudantesMatriculados();
        assertTrue(turmasComEstudantes.contains(turmaCadastradaComEstudantes));
        assertFalse(turmasComEstudantes.contains(turmaCadastradaSemEstudantes));
    }

    /**
     * Classe EstudantesMatriculados
     */

    @Test
    void deveriaMatricularEstudanteComSucesso() {
        // Criar uma instância de TurmaCurso para teste
        TurmaCurso turmaCurso = new TurmaCurso();
        turmaCurso.setLocal("Local Teste");
        turmaCurso.setVagas(10);
        turmaCurso.setVagasDisponiveis(10);
        turmaCurso.setInicioMatriculas(LocalDate.of(2022, 11, 1));
        turmaCurso.setFimMatriculas(LocalDate.of(2022, 12, 31));
        turmaCurso.setInicioAulas(LocalDate.of(2023, 1, 1));
        turmaCurso.setFimAulas(LocalDate.of(2024, 1, 1));

        // Criar uma instância de EstudantesMatriculados para teste
        EstudantesMatriculados estudantesMatriculados = new EstudantesMatriculados();

        // Criar uma instância de Estudante para teste
        Estudante estudante = new Estudante();
        estudante.setNome("João");
        estudante.setMatricula("12345");

        // Associar o estudante à instância de EstudantesMatriculados
        estudantesMatriculados.setEstudante(estudante);
        estudantesMatriculados.setTurmaCurso(turmaCurso);
        estudantesMatriculados.setDataMatricula(LocalDate.of(2022, 11, 5));

        // Certificar-se de que a lista de estudantes matriculados está inicializada
        turmaCurso.setEstudantesMatriculados(new ArrayList<>());

        // Chamar o método execute do serviço
        estudantesMatriculadosService.execute(estudantesMatriculados);

        // Obter a lista de estudantes matriculados após a execução
        List<EstudantesMatriculados> estudantesMatriculadosList = turmaCurso.getEstudantesMatriculados();

        // Verificar se a quantidade de vagas disponíveis foi atualizada corretamente
        assertEquals(9, turmaCurso.getVagasDisponiveis());

        // Verificar se o estudante foi associado à turma
        assertEquals(1, estudantesMatriculadosList.size());
        EstudantesMatriculados estudantesMatriculadosCadastrado = estudantesMatriculadosList.get(0);
        assertNotNull(estudantesMatriculadosCadastrado.getId());
        assertEquals(estudante, estudantesMatriculadosCadastrado.getEstudante());
        assertEquals(turmaCurso, estudantesMatriculadosCadastrado.getTurmaCurso());
    }

    @Test
    void deveriaLancarExcecaoAoMatricularEstudanteSemVagas() {
        // Criar uma instância de TurmaCurso para teste
        TurmaCurso turmaCurso = new TurmaCurso();
        turmaCurso.setLocal("Local Teste");
        turmaCurso.setVagas(1); //1 vaga apenas
        turmaCurso.setVagasDisponiveis(0); //atualizado para 0 vagas
        turmaCurso.setInicioMatriculas(LocalDate.of(2022, 11, 1));
        turmaCurso.setFimMatriculas(LocalDate.of(2022, 12, 31));
        turmaCurso.setInicioAulas(LocalDate.of(2023, 1, 1));
        turmaCurso.setFimAulas(LocalDate.of(2024, 1, 1));

        // Criar uma instância de Estudante para teste
        Estudante estudante = new Estudante();
        estudante.setNome("João");
        estudante.setMatricula("12345");

        // Chamar o método matricularEstudante sem vagas e esperar uma exceção
        assertThrows(RuntimeException.class, () -> estudantesMatriculadosService.execute(turmaCurso, estudante));
    }

    @Test
    void deveriaLancarExcecaoAoMatricularEstudanteForaDoPeriodo() {
        // Criar uma instância de TurmaCurso para teste
        TurmaCurso turmaCurso = new TurmaCurso();
        turmaCurso.setLocal("Local Teste");
        turmaCurso.setVagas(10);
        turmaCurso.setVagasDisponiveis(10);
        turmaCurso.setInicioMatriculas(LocalDate.of(2022, 11, 1));
        turmaCurso.setFimMatriculas(LocalDate.of(2022, 12, 31));
        turmaCurso.setInicioAulas(LocalDate.of(2023, 1, 1));
        turmaCurso.setFimAulas(LocalDate.of(2024, 1, 1));

        // Criar uma instância de Estudante para teste
        Estudante estudante = new Estudante();
        estudante.setNome("João");
        estudante.setMatricula("12345");

        // Chamar o método matricularEstudante fora do período e esperar uma exceção
        assertThrows(RuntimeException.class, () -> estudantesMatriculadosService.execute(turmaCurso, estudante));
    }

}
