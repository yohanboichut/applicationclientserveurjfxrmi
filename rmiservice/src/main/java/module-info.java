module rmiservice {
    requires java.rmi;
    requires modele;
    requires interfaces;
    exports rmiservice.application;
    exports rmiservice.service;
}