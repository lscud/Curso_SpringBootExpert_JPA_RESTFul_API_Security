package com.lscud.curso.exemploProjeto.domain.config;

import com.lscud.curso.exemploProjeto.domain.entity.Usuario;
import com.lscud.curso.exemploProjeto.domain.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class IntercionalizacaoConfig{

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("ISO-8859-1");
        messageSource.setDefaultLocale(Locale.getDefault());

        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean() { //responsavel por fazer a interpolação (pega nome da variavel e coloca conteudo)
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        return localValidatorFactoryBean;
    }


//    @Autowired
//    private UsuarioRepositorio repositorio;
//    @Override
//    public void run(String... args) throws Exception {
//        Usuario teste1 = new Usuario();
//        teste1.setLogin("Test");
//        teste1.setSenha("1");
//        repositorio.save(teste1);
//
//    }
}
