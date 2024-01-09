package coana.plugins;

import coana.SingletonDataHolder;
import pascal.taie.analysis.pta.core.solver.DeclaredParamProvider;
import pascal.taie.analysis.pta.core.solver.EntryPoint;
import pascal.taie.analysis.pta.core.solver.Solver;
import pascal.taie.analysis.pta.plugin.Plugin;

/**
* Dynamically add all methods in application classes as analysis entry points.
 */
public class AddLibEntryPointsPlugin implements Plugin {

    private Solver solver;

    @Override
    public void setSolver(Solver solver) {
        this.solver = solver;
    }

    @Override
    public void onStart() {
        try {
            SingletonDataHolder.getInstance().getAppClasses().stream()
                .map(cls -> this.solver.getHierarchy().getClass(cls))
                .flatMap(cls -> cls.getDeclaredMethods().stream())
                .filter(m -> !m.isAbstract())
                .map(m -> new EntryPoint(m, new DeclaredParamProvider(m, this.solver.getHeapModel())))
                .forEach(this.solver::addEntryPoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
