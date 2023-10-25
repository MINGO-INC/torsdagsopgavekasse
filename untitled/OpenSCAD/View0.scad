union()
{
    difference()
    {
        linear_extrude(height = 100.0, twist = 0.0, scale = 1.0, slices = 1, center = false)
        {
            scale([140.0, 90.0])
            {
                M8();
            }
        }
        translate([0.0, 0.0, 10.0])
        {
            linear_extrude(height = 100.0, twist = 0.0, scale = 1.0, slices = 1, center = false)
            {
                scale([130.0, 80.0])
                {
                    M8();
                }
            }
        }
    }
}

module M8()
{
    polygon
    (
        points =
        [
            [-0.5, -0.5], 
            [0.5, -0.5], 
            [0.5, 0.5], 
            [-0.5, 0.5]
        ],
        paths =
        [
            [0, 1, 2, 3]
        ]
    );
}
