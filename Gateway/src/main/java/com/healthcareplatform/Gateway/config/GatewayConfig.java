package com.healthcareplatform.Gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private static final String LB = "lb://";

    @Value("${gateway.services.authenticationService}")
    private String authenticationService;

    @Value("${gateway.services.analyticsService}")
    private String analyticsService;

    @Value("${gateway.services.appointmentService}")
    private String appointmentService;

    @Value("${gateway.services.auditLoggingService}")
    private String auditLoggingService;

    @Value("${gateway.services.billingClaimsService}")
    private String billingClaimsService;

    @Value("${gateway.services.inventoryService}")
    private String inventoryService;

    @Value("${gateway.services.notificationService}")
    private String notificationService;

    @Value("${gateway.services.patientService}")
    private String patientService;

    @Value("${gateway.services.pharmacyService}")
    private String pharmacyService;

    @Value("${gateway.services.staffService}")
    private String staffService;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                // Auth routes
                .route("auth_login_route", r -> r
                        .path("/login")
                        .filters(f -> f.rewritePath("/login", "/api/v1/auth/public/login"))
                        .uri(LB + authenticationService))

                .route("auth_logout_route", r -> r
                        .path("/auth/logout")
                        .filters(f -> f.rewritePath("/auth/logout", "/api/v1/auth/logout"))
                        .uri(LB + authenticationService))

                .route("auth_token_refresh_route", r -> r
                        .path("/auth/token/refresh")
                        .filters(f -> f.rewritePath("/auth/token/refresh", "/api/v1/auth/token/refresh"))
                        .uri(LB + authenticationService))

                .route("auth_route", r -> r
                        .path("/api/v1/auth/**")
                        .uri(LB + authenticationService))

                // Patient routes
                .route("patient_route", r -> r
                        .path("/api/v1/patients/**")
                        .uri(LB + patientService))

                // Appointment routes
                .route("appointment_route", r -> r
                        .path("/api/v1/appointments/**")
                        .uri(LB + appointmentService))

                // Pharmacy routes
                .route("pharmacy_route", r -> r
                        .path("/api/v1/pharmacy/**")
                        .uri(LB + pharmacyService))

                // Staff routes
                .route("staff_route", r -> r
                        .path("/api/v1/staff/**")
                        .uri(LB + staffService))

                // Billing & Claims routes
                .route("billing_route", r -> r
                        .path("/api/v1/billing/**")
                        .uri(LB + billingClaimsService))

                // Inventory routes
                .route("inventory_route", r -> r
                        .path("/api/v1/inventory/**")
                        .uri(LB + inventoryService))

                // Notification routes
                .route("notification_route", r -> r
                        .path("/api/v1/notifications/**")
                        .uri(LB + notificationService))

                // Analytics routes
                .route("analytics_route", r -> r
                        .path("/api/v1/analytics/**")
                        .uri(LB + analyticsService))

                // Audit Logging routes
                .route("audit_route", r -> r
                        .path("/api/v1/audit/**")
                        .uri(LB + auditLoggingService))

                .build();
    }
}
