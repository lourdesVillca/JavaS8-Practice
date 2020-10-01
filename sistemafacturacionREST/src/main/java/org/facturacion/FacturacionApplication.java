package org.facturacion;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.facturacion.resources.FacturaController;
import org.facturacion.resources.StatsController;

public class FacturacionApplication extends Application<FacturacionConfiguration> {

    public static void main(final String[] args) throws Exception {
        new FacturacionApplication().run(args);
    }

    @Override
    public String getName() {
        return "Facturacion";
    }

    @Override
    public void initialize(final Bootstrap<FacturacionConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final FacturacionConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new FacturaController());
        environment.jersey().register(new StatsController());
    }

}
