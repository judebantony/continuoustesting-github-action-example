package com.jba.ci.ct.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author judebarnabasantony
 *
 */

@RestController
@RequestMapping("/api/v1/ci/ct")
public class CTGitHubActionRestController {

	private static final String JUDE_S_GIT_HUB_ACTION_CT_EXAMPLE = "Jude's GitHub Action CT Example";

	@GetMapping("/health")
    public String health() {
        return JUDE_S_GIT_HUB_ACTION_CT_EXAMPLE;
    }
}
