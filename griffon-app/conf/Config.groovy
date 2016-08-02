application {
    title = 'invoicer'
    startupGroups = ['invoicer']
    autoShutdown = true
}
mvcGroups {
    // MVC Group for "invoicer"
    'invoicer' {
        model      = 'org.example.InvoicerModel'
        view       = 'org.example.InvoicerView'
        controller = 'org.example.InvoicerController'
    }
}